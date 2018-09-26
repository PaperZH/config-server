<template>
  <el-row class="wrap">
    <el-col :span="24" class="warp-breadcrum">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
        <el-breadcrumb-item>推荐课程管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>
    <el-col :span="24" class="wrap-main" v-loading="loading" element-loading-text="拼命加载中">
       <!--工具条-->
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form :inline="true" :model="reccourse">
          <el-form-item>
            <el-input v-model="reccourse.courseName" placeholder="课程名" @keyup.enter.native="handleSearch"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-on:click="handleSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="showAddDialog">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <!-- 列表 -->
      <el-table :data="courseRows" border highlight-current-row v-loading="loading" style="width: 100%;">
        <el-table-column label="预览" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.courseCover" height="100">
          </template>
        </el-table-column>
        <el-table-column label="状态" prop="scope" align="center">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 0" type="danger">已删除</el-tag>
            <el-tag v-if="scope.row.status === 1" type="success">正常</el-tag>
            <el-tag v-if="scope.row.status === 2" type="warning">已失效</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="老师姓名" prop="teacherName" width="120" align="center"></el-table-column>
        <el-table-column label="课程名" prop="courseName" width="150" align="center"></el-table-column>
        <el-table-column label="课程类型" prop="typeName" width="80"  align="center"></el-table-column>
         <el-table-column label="点赞数" prop="praiseNum" width="80"  align="center"></el-table-column>
        <el-table-column label="排序" prop="orderNum" width="80"  align="center"></el-table-column>       
        <el-table-column label="推荐信息" prop="recCourseInfo"  align="center"></el-table-column>
        <el-table-column label="课程介绍" prop="courseDescription"  align="center"></el-table-column>  
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
              :page-sizes="[8, 16, 32]">
        </el-pagination>
      </el-col>
      <!--新增界面-->
      <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
          <el-form-item label="课程名" prop="title">
                <el-autocomplete
                  popper-class="my-autocomplete"
                  v-model="courseName"
                  :fetch-suggestions="querySearch"
                  placeholder="请输入内容"
                  @select="handleSelect">
                <i
                  class="el-icon-edit el-input__icon"
                  slot="suffix"
                  >
                </i>
                <template slot-scope="{ item }">
                  <div class="name">{{ item.courseName }}</div>
                </template></el-autocomplete>         
             
          </el-form-item>
          <el-form-item label="排序" prop="orderNum">
            <el-input v-model="addForm.orderNum" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="推荐信息" prop="description" >
            <el-input
                type="textarea"
                :autosize="{ minRows: 3, maxRows: 6}"
                placeholder="请输入内容"
                v-model="addForm.description">
              </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="addFormVisible = false">取消</el-button>
          <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
        </div>
      </el-dialog>

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
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="editFormVisible = false">取消</el-button>
          <el-button type="primary" @click.native="editSubmit" :loading="addLoading">提交</el-button>
        </div>
      </el-dialog>

    </el-col>
  </el-row>
</template>

<script>
  import API from '../../api/api_reccourse'

  export default {
    name: "List",
    data() {
      return {
        reccourse: {
          courseName: ''
        },
        sels:[],
        courseRows:[],
        ids:[],
        courseName:'',
        total: 15,
        page: 1,
        limit: 8,
        loading: false,
        addFormVisible: false,//新增界面是否显示
        addLoading: false,
        addFormRules: {
          courseName: [
            {required: true, message: '请输入课程', trigger: 'blur'}
          ]
        },
        //新增推荐课程 数据
        addForm: {
          courseId: '',
          orderNum: '',
          description: ''
        },
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
      //关于输入课程名称的查询方法（Element自带的方法体系）
       querySearch(queryString, cb) {
        this.searchCourse(queryString);
        // 调用 callback 返回建议列表的数据
        var data = this.ids;
        cb(data);
      },
      handleSelect(item) { 
        const data = item;
        this.addForm.courseId = data.id;
        this.courseName = data.courseName;
      },
      handleSizeChange(val) {
        this.limit = val;
        this.searchRecCourse();
      },
      handleCurrentChange(val) {
        this.page = val;
        this.searchRecCourse();
      },
      handleSearch() {
        this.page = 1;
        this.searchRecCourse();
      },
      searchCourse: function (query) {
        let that = this;
        let params = {
          courseName: query
        }
        that.loading = true;
        API.getCourse(params).then(
            function (result) {
              that.coursedata = result;
              that.loading = false;
              that.ids = result.listCourse.ids;
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
       searchRecCourse: function () {
        let that = this;
        let params = {
          currentPage: that.page,
          pageSize: that.limit,
          courseName: that.reccourse.courseName
        }
        that.loading = true;
        API.getRecCourse(params).then(
            function (result) {
              that.loading = false;
              if (result&& result.code == 0) {
                
                that.courseRows = result.list;
                that.loading=false;
              }
            },
            function (err) {
              that.loading = false;
              that.$message.error({
                showClose: true,
                message: err.toString(),
                duration: 2000
              });
            }
          )
          .catch(function (error) {
            that.loading = false;
            that.$message.error({
              showClose: true,
              message: "请求出现异常",
              duration: 2000
            });
          });
      },


      showAddDialog: function () {
        this.addFormVisible = true
      },
      showEditDialog: function (index, row) {
        let that = this
        this.editFormVisible = true
        this.editForm.courseId = row.courseId
        this.editForm.description = row.recCourseInfo
        this.editForm.orderNum = row.orderNum
      },

      //新增
      addSubmit: function () {
        let that = this;
        this.$refs.addForm.validate((valid) => {
          if (valid) {
            that.loading = true;
            let para = Object.assign({}, this.addForm);
            API.add(para).then(function (result) {
              that.loading = false;
              if (result && parseInt(result.code) === 0) {
                that.$message.success({showClose: true, message: '新增成功', duration: 2000});
                // that.$refs['addForm'].resetFields();
                that.addFormVisible = false;
                that.searchRecCourse();
              } else {
                that.$message.error({showClose: true, message: '新增失败', duration: 2000});
              }
            }, function (err) {
              that.loading = false;
              that.$message.error({showClose: true, message: err.toString(), duration: 2000});
            }).catch(function (error) {
              that.loading = false;
              that.$message.error({showClose: true, message: '请求出现异常', duration: 2000});
            });

          }
        });
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
      },
       handleBatchRemove: function (rows) {
        let idsIds = [];
        rows.forEach(element =>{
          idsIds.push(element.id)
        })
        let that = this;
        let params = Object.assign({}, {id:idsIds});
        this.$confirm("确认删除选中记录吗?", "提示", {type: "warning"})
          .then(() => {
            that.loading = true;
            API.batchRemove(params)
              .then(
                function (result) {
                  that.loading = false;
                  if (result && parseInt(result.code) === 0) {
                    that.$message.success({
                      showClose: true,
                      message: "批量删除成功",
                      duration: 1500
                    });
                    that.searchRecCourse();
                  }
                },
                function (err) {
                  that.loading = false;
                  that.$message.error({
                    showClose: true,
                    message: err.toString(),
                    duration: 2000
                  });
                }
              )
              .catch(function (error) {
                that.loading = false;
                that.$message.error({
                  showClose: true,
                  message: "请求出现异常",
                  duration: 2000
                });
              });
          })
          .catch(() => {
          });
      }
      
    },

    mounted() {
      this.searchRecCourse();
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
