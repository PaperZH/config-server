<template>
  <el-row class="wrap">
    <el-col :span="24" class="warp-breadcrum">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
        <el-breadcrumb-item>banner推荐管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>
    <el-col :span="24" class="wrap-main" v-loading="loading" element-loading-text="拼命加载中">
       <!--工具条-->
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form :inline="true" :model="filters">
          <el-form-item>
            <el-input v-model="filters.title" placeholder="标题" @keyup.enter.native="handleSearch"></el-input>
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
      <el-table :data="fileRows" border highlight-current-row v-loading="loading" style="width: 100%;">
        <el-table-column label="预览" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.bannerImg" height="40">
          </template>
        </el-table-column>
        <el-table-column label="类型" width="80" prop="type" align="center">
          <template slot-scope="scope">
            <el-tag >图片</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="标题" prop="bannerTitle" width="240" align="center"></el-table-column>
        <el-table-column label="描述" prop="bannerDescription" width="420" align="center"></el-table-column>
        <el-table-column label="排序" prop="orderNum" width="80"  align="center"></el-table-column>
        <el-table-column label="跳转地址" prop="bannerUrl" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="showEditDialog(scope.$index,scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="removeFile(scope.row.id)">删除</el-button>
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
<!-- 
      <el-col :span="24" class="version" style="margin-top:20px">
        <div style="margin-left: auto; margin-right: auto;text-align: center">
          <span>版权所有版权所有版权所有版权所有版权所有版权所有</span>
        </div>
        <div style="margin-left: auto; margin-right: auto;text-align: center">
          <span>版权所有版权所有版权所有版权所有版权所有版权所有版权所有版权所有版权所有版权所有版权所有</span>
        </div>
      </el-col> -->
      

      <!--新增界面-->
      <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
          <el-form-item label="标题" prop="bannerTitle">
            <el-input v-model="addForm.bannerTitle" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="内容" prop="bannerDescription">
            <el-input type="textarea" v-model="addForm.bannerDescription" :rows="2"></el-input>
          </el-form-item>
          <el-form-item label="排序" prop="orderNum">
            <el-input v-model="addForm.orderNum" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="跳转地址" prop="bannerUrl">
            <el-input v-model="addForm.bannerUrl" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="上传图片" prop="bannerImg">
            <el-input v-model="addForm.bannerImg" auto-complete="off" placeholder="上传模板只能是 jpg/jpeg/png/gif 格式，且不超过5MB"></el-input>
            <div slot="tip" class="el-upload__tip"></div>
            <el-upload
             class="avatar-uploader"
              action=""
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="addImageUrl" :src="addImageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="addFormVisible = false">取消</el-button>
          <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
        </div>
      </el-dialog>

      <!-- 编辑界面 -->
      <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
        <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
          <el-form-item label="标题" prop="bannerTitle">
            <el-input v-model="editForm.bannerTitle" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="内容" prop="bannerDescription">
            <el-input type="textarea" v-model="editForm.bannerDescription" :rows="2"></el-input>
          </el-form-item>
          <el-form-item label="排序" prop="orderNum">
            <el-input v-model="editForm.orderNum" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="跳转地址" prop="bannerUrl">
            <el-input v-model="editForm.bannerUrl" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="上传图片" prop="bannerImg">
            <el-input v-model="editForm.bannerImg" auto-complete="off"></el-input>
            <el-upload
             class="avatar-uploader"
              action=""
              :show-file-list="false"
              :on-success="editHandleAvatarSuccess"
              :before-upload="editBeforeAvatarUpload">
              <div slot="tip" class="el-upload__tip" >上传模板只能是 jpg/jpeg/png/gif 格式，且不超过5MB</div>
              <img v-if="editForm.url" :src="editForm.url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
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
  import API from '../../api/api_banner'

  export default {
    name: "List",
    data() {
      return {
        filters: {
          title: ''
        },
        total: 0,
        page: 1,
        limit: 10,
        loading: false,
        fileRows: [],
        addImageUrl: '',
        //新增相关数据
        addFormVisible: false,//新增界面是否显示
        addLoading: false,
        addFormRules: {
          bannerTitle: [
            {required: true, message: '请输入标题', trigger: 'blur'}
          ]
        },
        addForm: {
          bannerTitle: '',
          bannerDescription: '',
          orderNum: '',
          bannerImg: '',
          bannerUrl: ''
        },
        editFormVisible: false,
        editFormRules: {
          title: [
            {required: true, message: '请输入标题', trigger: 'blur'}
          ]
        },
        editForm: {
          bannerTitle: '',
          bannerDescription: '',
          orderNum: '',
          bannerImg: '',
          bannerUrl: ''
        },
      }
    },
    methods: {
      handleSizeChange(val) {
        this.limit = val;
        this.search();
      },
      handleCurrentChange(val) {
        this.page = val;
        this.search();
      },
      handleSearch() {
        this.total = 0;
        this.page = 1;
        this.search();
      },
      search: function () {
        let that = this;
        let params = {
          page: that.page,
          limit: that.limit,
          bannerTitle: that.filters.title
        }
        that.loading = true;
        
        API.banners(params).then(
            function (result) {
              console.log(result)
              that.loading = false;
              if (result && result.page.rows) {
                that.total = result.page.total;
                that.fileRows = result.page.rows;
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
            console.log(error);
            that.$message.error({
              showClose: true,
              message: "请求出现异常",
              duration: 2000
            });
          });
      },

      showAddDialog: function () {
        this.addFormVisible = true
        this.addForm = {}
      },
      showEditDialog: function (index, row) {
        let that = this
        this.editFormVisible = true
        this.editForm = Object.assign({}, row)
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
                that.$refs['addForm'].resetFields();
                that.addFormVisible = false;
                that.search();
              } else {
                that.$message.error({showClose: true, message: '新增失败', duration: 2000});
              }
            }, function (err) {
              that.loading = false;
              that.$message.error({showClose: true, message: err.toString(), duration: 2000});
            }).catch(function (error) {
              that.loading = false;
              console.log(error);
              that.$message.error({showClose: true, message: '请求出现异常', duration: 2000});
            });

          }
        });
      },

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
                that.search();
              } else {
                that.$message.error({
                  showClose: true,
                  message: "修改失败",
                  duration: 2000
                });
              }
            });
          }
        });
      },

      removeFile: function (id) {
        let that = this
        return API.remove({id: id}).then(res => {
          if (res.code === 0) {
            that.$message.success(res.msg)
            that.search(this.page);
          }
        })
      },
      beforeAvatarUpload(file) {
        let that = this
        const extension = file.name.split('.')[1] === 'jpg'
        const extension2 = file.name.split('.')[1] === 'jpeg'
        const extension3 = file.name.split('.')[1] === 'png'
        const extension4 = file.name.split('.')[1] === 'gif'

        const isLt2M = file.size / 1024 / 1024 < 5;

        if (!extension && !extension2 && !extension3 && !extension4) {
          console.log('上传模板只能是 jpg/jpeg/png/gif 格式!')
          return false
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 5MB!');
          return false;
        }

        let fd = new FormData();                                                                                                                                                                                                                                                                                                                                      
        fd.append('file', file);
        API.uploadBanner(fd).then(function (result) {
          if (result && parseInt(result.code) === 0) {
            that.addImageUrl = result.fileUrl
            that.addForm.imgUrl = that.addImageUrl
            that.$message.success({
              showClose: true,
              message: "上传成功",
              duration: 2000
            });
          } else {
            that.$message.error({
              showClose: true,
              message: "上传失败",
              duration: 2000
            });
          }
        });
        return false;
      },

      handleAvatarSuccess(res, file) {
          console.log(res);
      },

      editBeforeAvatarUpload(file) {
        let that = this
        const extension = file.name.split('.')[1] === 'jpg'
        const extension2 = file.name.split('.')[1] === 'jpeg'
        const extension3 = file.name.split('.')[1] === 'png'
        const extension4 = file.name.split('.')[1] === 'gif'
        const isLt2M = file.size / 1024 / 1024 < 5;

        if (!extension && !extension2 && !extension3 && !extension4) {
          console.log('上传模板只能是 jpg/jpeg/png/gif 格式!')
          return false
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 5MB!');
          return false;
        }

        let fd = new FormData();                                                                                                                                                                                                                                                                                                                                      
        fd.append('file', file);
        API.uploadFile(fd).then(function (result) {
          if (result && parseInt(result.code) === 0) {
            that.editForm.bannerImg = result.fileUrl
            that.$message.success({
              showClose: true,
              message: "上传成功",
              duration: 2000
            });
          } else {
            that.$message.error({
              showClose: true,
              message: "上传失败",
              duration: 2000
            });
          }
        });
        return false;
      },

      editHandleAvatarSuccess(res, file) {
          console.log(res);
      }
    },

    mounted() {
      this.search(1);
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
      /* width: fit-content;
      margin-left: auto;
      margin-right: auto;
      text-align: center */
    }
  </style>
