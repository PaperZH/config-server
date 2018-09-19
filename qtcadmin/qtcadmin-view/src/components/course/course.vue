<template>
  <el-row class="wrap">
    <el-col :span="24" class="warp-breadcrum">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
        <el-breadcrumb-item>课程管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>
    <el-col :span="24" class="wrap-main" v-loading="loading" element-loading-text="拼命加载中">
       <!--工具条-->
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form :inline="true" :model="course">
          <el-form-item>
            <el-input v-model="course.courseName" placeholder="课程名" @keyup.enter.native="handleSearch"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-on:click="handleSearch">查询</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <!-- 列表 -->
      <el-table :data="courseRows" border highlight-current-row v-loading="loading" style="width: 100%;">
        <el-table-column label="预览" align="center" >
          <template slot-scope="scope">
            <img :src="scope.row.courseCover" height="100">
          </template>
        </el-table-column>
        <el-table-column label="课程名" prop="courseName" width="120" align="center"></el-table-column>
        <el-table-column label="课程类型" prop="typeName" width="80"  align="center"></el-table-column>
         <el-table-column label="点赞数" prop="praiseNum" width="80"  align="center"></el-table-column>      
        <el-table-column label="课程介绍" prop="courseDescription" align="center"></el-table-column>  
        <el-table-column label="失效时间" prop="invalidDate" align="center"></el-table-column>   
       <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <!--  @click="showEditDialog(scope.$index,scope.row)" -->
            <el-button size="mini">编辑</el-button>
            <!-- type="danger" @click="removeRecCourse(scope.row.courseId)" -->
            <el-button size="mini" >删除</el-button>
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

      <!-- 编辑界面 -->
      <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
        <el-form :model="editForm" label-width="80px" ref="editForm">
          <el-form-item label="排序" prop="orderNum">
            <el-input v-model="editForm.orderNum" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="推荐信息" prop="orderNum">
             <el-input
                type="textarea"
                :autosize="{ minRows: 3, maxRows: 6}"
                placeholder="请输入内容"
                v-model="editForm.description">
              </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer" aria-disabled="true">
          <el-button @click.native="editFormVisible = false">取消</el-button>
          <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
        </div>
      </el-dialog>

    </el-col>
  </el-row>
</template>

<script>
  import API from '../../api/api_course'

  export default {
    name: "List",
    data() {
      return {
        course: {
          courseName: ''
        },
        courseRows:[],
        courseName:'',
        total: 0,
        page: 1,
        limit: 10,
        loading: false,
        editLoading: false,
        //编辑推荐课程 数据
        editFormVisible: false,
        editForm: {
           courseId: '',
           orderNum: '',
           description: ''
        },
      }
    },
    methods: {
      handleSelect(item) { 
        const data = item;
        this.addForm.courseId = data.id;
        this.courseName = data.courseName;
      },
      handleSizeChange(val) {
        this.limit = val;
        this.searchCourse();
      },
      handleCurrentChange(val) {
        this.page = val;
        this.searchCourse();
      },
      handleSearch() {
        this.total = 0;
        this.page = 1;
        this.searchCourse();
      },
      searchCourse: function () {
        let that = this;
        let params = {
          courseName: this.course.courseName,
          currentPage: this.page,
          pageSize: this.limit
        }
        that.loading = true;
        API.getCourseList(params).then(
            function (result) {
              that.courseRows = result.re.rows;
              that.loading = false;
              that.total = result.re.total
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
       removeRecCourse: function (id) {
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
            that.editLoading = true;
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
              }
               that.editLoading = false;
            }).catch(function (error) {
            that.editLoading = false;
            that.$message.error({
              message: "请求出现异常",
              duration: 2000
            });
          });;
          }
        });
      }
    },
    mounted() {
      this.searchCourse();
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
