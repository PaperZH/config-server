<template>
  <el-row class="wrap">
    <el-col :span="24" class="warp-breadcrum">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
        <el-breadcrumb-item>课件管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>
    <el-col :span="24" class="wrap-main" v-loading="loading" element-loading-text="拼命加载中">
       <!--工具条-->
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form :inline="true" :model="courseware">
          <el-form-item>
            <el-input v-model="courseware.coursewareName" placeholder="课程名" @keyup.enter.native="handleSearch"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-on:click="handleSearch">查询</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <!-- 列表 -->
      <el-table :data="courseRows" border highlight-current-row v-loading="loading" style="width: 100%;">
        <el-table-column label="课件名" prop="coursewareName" width="120" align="center"></el-table-column>
        <el-table-column label="课件类别" prop="coursewareType" width="80" align="center"></el-table-column>
        <el-table-column label="课件描述" prop="coursewareDescription" align="center"></el-table-column>
        <el-table-column label="元课件名" prop="baseCoursewareName" width="120" align="center"></el-table-column>
        <el-table-column label="元课件描述" prop="baseCoursewareDescription" align="center"></el-table-column>
        <el-table-column label="预览地址" prop="preUrl" align="center"></el-table-column>      
        <el-table-column label="文件地址" prop="sourceUrl" align="center"></el-table-column>  
       <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="showEditDialog(scope.$index,scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="removeRecCourse(scope.row.courseId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--工具条-->
      <el-col :span="24" class="toolbar" >
        <el-pagination layout="total,sizes, prev,pager, next,jumper" background
              @size-change="handleSizeChange"  
              @current-change="handleCurrentChange" 
              :page-size="limit"
              :total="total"
              :page-sizes="[10, 20, 30]">
        </el-pagination>
      </el-col>
    </el-col>
  </el-row>
</template>

<script>
  import API from '../../api/api_courseware'

  export default {
    name: "List",
    data() {
      return {
        courseware: {
          coursewareName: ''
        },
        courseRows:[],
        total: 0,
        page: 1,
        limit: 10,
        loading: false,
      }
    },
    methods: {
      handleSizeChange(val) {
        this.limit = val;
        this.searchCourseware();
      },
      handleCurrentChange(val) {
        this.page = val;
        this.searchCourseware();
      },
      handleSearch() {
        this.total = 0;
        this.page = 1;
        this.searchCourseware();
      },
      searchCourseware: function () {
        let that = this;
        let params = {
          coursewareName: this.courseware.coursewareName,
          pageNo: this.page,
          pageSize: this.limit
        }
        that.loading = true;
        API.getCoursewareList(params).then(
            function (result) {
              that.courseRows = result.re.backCoursewareDTOList;
              that.loading = false;
              that.total = result.re.count
            }
          )
          .catch(function (error) {
            that.loading = false;
            console.log(error);
            that.$message.error({
              message: "请求出现异常",
              duration: 2000
            });
          });
      },
      showEditDialog: function (index, row) {
        let that = this
        this.editFormVisible = true
        this.editForm.courseId = row.courseId
        this.editForm.description = row.recCourseInfo
        this.editForm.orderNum = row.orderNum
      },
      //删除
       removeRecCourseware: function (id) {
        let that = this
        return API.remove({id: id}).then(res => {
          
          if (res.code === 0) {
            that.searchRecCourse();
            that.$message.success(res.msg);
            
          }else{
             that.$message.error(res.msg);
          }
        })
      },
      
      //编辑
      editSubmit: function () {
        let that = this;
        this.$refs.editForm.validate(valid => {
          if (valid) {
            that.loading = true;
            let params = Object.assign({}, that.editForm);
            API.update(params).then(function (result) {
              if (result && parseInt(result.code) === 0) {
                that.$message.success({
                  showClose: true,
                  message: "修改成功",
                  duration: 2000
                });
                that.$refs["editForm"].resetFields();
                that.editFormVisible = false;
                that.searchRecCourse();
              } else {
                that.$message.error({
                  showClose: true,
                  message: "修改失败",
                  duration: 2000
                });
                that.editFormVisible = false;
                that.loading = false;
              }
            });
          }
        });
      }
    },
    mounted() {
      this.searchCourseware();
    }
  }
</script>

<style>
    .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #20a0ff;
    }
    .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      line-height: 178px;
      text-align: center;
    }
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }

    .el-pagination {
      margin-top: 15px;
      margin-bottom: 15px;
      float: right;
    }
  </style>
