<template>
  <el-row class="warp">
    <el-col :span="24" class="warp-breadcrum">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
        <el-breadcrumb-item>用户列表</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>

    <el-col :span="24" class="warp-main" v-loading="loading" element-loading-text="拼命加载中">
      <!--工具条-->
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form :inline="true" :model="filters">
          <el-form-item>
            <el-input v-model="filters.name" placeholder="姓名" style="min-width: 240px;"
                      @keyup.enter.native="handleSearch"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="showAddDialog">添加</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <!--列表-->
      <el-table :data="users" border highlight-current-row v-loading="loading" style="width: 100%;">
        <!-- <el-table-column type="selection" width="60">
        </el-table-column> -->
        <el-table-column prop="username" label="用户名" width="100" align="center" sortable>
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="100" align="center" sortable>
        </el-table-column>
        <el-table-column prop="nickname" label="昵称" width="100" align="center" sortable>
        </el-table-column>
        <el-table-column prop="userno" label="工号" width="80" align="center">
        </el-table-column>
        <!-- <el-table-column prop="sex" label="性别" width="60" :formatter="formatSex" align="center">
        </el-table-column> -->
        <el-table-column prop="deptName" label="部门" width="120" align="center">
        </el-table-column>
        <el-table-column prop="mobile" label="手机" width="120" align="center">
        </el-table-column>
        <el-table-column prop="email" label="邮箱" min-width="180" align="center" sortable>
        </el-table-column>
        <el-table-column label="图像" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.avatar" height="48">
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150" align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="showEditDialog(scope.$index,scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="removeUser(scope.$index,scope.row)">删除</el-button>
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

      <!-- 添加界面 -->
      <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="addForm.username" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="addForm.password" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="addForm.name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="昵称" prop="nickname">
            <el-input v-model="addForm.nickname" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="工号" prop="userno">
            <el-input v-model="addForm.userno" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="出生日期" prop="birth">
            <el-date-picker type="date" placeholder="出生日期" v-model="addForm.birth" value-format="yyyy-MM-dd"></el-date-picker>
          </el-form-item>
          <el-form-item label="手机号" prop="mobile">
            <el-input v-model="addForm.mobile" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input type="email" v-model="addForm.email"></el-input>
          </el-form-item>
          <el-form-item label="角色" prop="roleIds">
            <el-checkbox-group v-model="roleIds">
              <el-checkbox v-for="role in roles" :label="role.roleId" :key="role.roleId">{{role.roleName}}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="部门">
            <el-tree
              :data="depts"
              node-key="id"
              show-checkbox
              :props="treeProps"
              @node-click="handleAddNodeClick"
              @check-change="addCheckChange"
              ref="deptAddTree">
            </el-tree>
          </el-form-item>
          <el-form-item label="上传图片" prop="avatar">
            <el-input v-model="addForm.avatar" auto-complete="off" placeholder="上传模板只能是 jpg/jpeg/png/gif 格式，且不超过5MB"></el-input>
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
          <el-form-item label="用户名" prop="username">
            <el-input v-model="editForm.username" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="editForm.name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="昵称" prop="nickname">
            <el-input v-model="editForm.nickname" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="工号" prop="userno">
            <el-input v-model="editForm.userno" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="出生日期" prop="birth">
            <el-date-picker type="date" placeholder="出生日期" v-model="editForm.birth" value-format="yyyy-MM-dd"></el-date-picker>
          </el-form-item>
          <el-form-item label="手机号" prop="mobile">
            <el-input v-model="editForm.mobile" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input type="email" v-model="editForm.email"></el-input>
          </el-form-item>
          <el-form-item label="角色" prop="roleIds">
            <el-checkbox-group v-model="roleIds">
              <el-checkbox v-for="role in roles" :label="role.roleId" :key="role.roleId">{{role.roleName}}
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="部门">
            <el-tree
              :data="depts"
              node-key="id"
              show-checkbox
              :props="treeProps"
              @node-click="handleAddNodeClick"
              @check-change="addCheckChange"
              ref="deptAddTree">
            </el-tree>
          </el-form-item>
          <el-form-item label="上传图片" prop="avatar">
            <el-input v-model="editForm.avatar" auto-complete="off"></el-input>
            <el-upload
             class="avatar-uploader"
              action=""
              :show-file-list="false"
              :on-success="editHandleAvatarSuccess"
              :before-upload="editBeforeAvatarUpload">
              <div slot="tip" class="el-upload__tip" >上传模板只能是 jpg/jpeg/png/gif 格式，且不超过5MB</div>
              <img v-if="editForm.avatar" :src="editForm.avatar" class="avatar">
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
import API from '../../api/api_user'
import ROLE_API from '../../api/api_role'
import DEPT_API from '../../api/api_dept'
import FILE_API from '../../api/api_file'

export default {
  data () {
    return {
      filters: {
        name: ''
      },
      loading: false,
      users: [],
      roles: [],
      roleIds: [],
      total: 0,
      page: 1,
      limit: 10,

      addImageUrl: '',
      editCheckId: '',
      depts: [],
      deptId: '',
      treeProps: {
        children: 'children',
        label: 'text'
      },
      editFormVisible: false,
      editFormRules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [{required: true, message: '请输入作者', trigger: 'blur'}],
        nickname: [
          {required: true, message: '请输入用户昵称', trigger: 'blur'}
        ],
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}]
      },
      editForm: {
        username: '',
        password: '',
        name: '',
        nickname: '',
        email: '',
        roleIds: []
      },
      // 新增相关数据
      addFormVisible: false, // 新增界面是否显示
      addLoading: false,
      addFormRules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [{required: true, message: '请输入作者', trigger: 'blur'}],
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}]
      },
      addForm: {
        username: '',
        password: '',
        name: '',
        nickname: '',
        email: '',
        roleIds: []
      }
    }
  },
  methods: {
    // 性别显示转换
    formatSex: function (row, column) {
      return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
    },
    handleSizeChange (val) {
      this.limit = val
      this.search()
    },
    handleCurrentChange (val) {
      this.page = val
      this.search()
    },
    handleSearch () {
      this.total = 0
      this.page = 1
      this.search()
    },
    // 获取用户列表
    search: function () {
      let that = this
      let params = {
        page: that.page,
        limit: 10,
        name: that.filters.name
      }

      that.loading = true
      API.findList(params)
        .then(
          function (result) {
            that.loading = false
            if (result && result.page.rows) {
              that.total = result.page.total
              that.users = result.page.rows
            }
          },
          function (err) {
            that.loading = false
            that.$message.error({
              showClose: true,
              message: err.toString(),
              duration: 2000
            })
          }
        )
        .catch(function (error) {
          that.loading = false
          console.log(error)
          that.$message.error({
            showClose: true,
            message: '请求出现异常',
            duration: 2000
          })
        })
    },
    showAddDialog: function () {
      let that = this
      this.addFormVisible = true
      that.roleIds = []
      ROLE_API.findList('').then(function (result) {
        that.roles = result.rows
      })
      DEPT_API.depts('').then(function (result) {
        if (!result.code) {
          that.depts = result
        }
      })
    },
    showEditDialog: function (index, row) {
      let that = this
      that.roleIds = []
      that.depts = []
      let keys = [row.deptId]
      this.editFormVisible = true
      this.editForm = Object.assign({}, row)
      ROLE_API.findList('').then(function (result) {
        that.roles = result.rows
      })
      ROLE_API.findById(row.userId).then(function (result) {
        if (!result.code) {
          that.roleIds = result
        }
      })
      DEPT_API.depts('').then(function (result) {
        if (!result.code) {
          that.depts = result
        }
      })
      that.$nextTick(function () {
        that.setTreeDeptId(keys)
      })
    },
    addSubmit: function () {
      let that = this
      this.$refs.addForm.validate(valid => {
        if (valid) {
          that.loading = true
          let params = Object.assign({}, this.addForm)
          params.roleIds = that.roleIds
          params.deptId = that.editCheckId
          API.addUser(params).then(function (result) {
            if (result.code === 0) {
              that.loading = false
              that.$message.success({
                showClose: true,
                message: '新增成功',
                duration: 2000
              })
              that.$refs['addForm'].resetFields()
              that.addFormVisible = false
              that.search()
            } else {
              that.$message.error({
                showClose: true,
                message: '修改失败',
                duration: 2000
              })
            }
          })
        }
      })
    },
    editSubmit: function () {
      let that = this
      this.$refs.editForm.validate(valid => {
        if (valid) {
          that.loading = true
          let params = Object.assign({}, that.editForm)
          params.roleIds = that.roleIds
          params.deptId = that.editCheckId
          API.editUser(params).then(function (result) {
            if (result.code === 0) {
              that.$message.success({
                showClose: true,
                message: '修改成功',
                duration: 2000
              })
              that.$refs['editForm'].resetFields()
              that.editFormVisible = false
              that.search()
            } else {
              that.$message.error({
                showClose: true,
                message: '修改失败',
                duration: 2000
              })
            }
          })
        }
      })
    },
    removeUser: function (index, row) {
      let that = this
      this.$confirm('确认删除该记录吗?', '提示', {type: 'warning'})
        .then(() => {
          that.loading = true
          API.removeUser({id: row.userId})
            .then(
              function (result) {
                that.loading = false
                if (result && parseInt(result.code) === 0) {
                  that.$message.success({
                    showClose: true,
                    message: '删除成功',
                    duration: 1500
                  })
                  that.search()
                }
              },
              function (err) {
                that.loading = false
                that.$message.error({
                  showClose: true,
                  message: err.toString(),
                  duration: 2000
                })
              }
            )
            .catch(function (error) {
              that.loading = false
              console.log(error)
              that.$message.error({
                showClose: true,
                message: '请求出现异常',
                duration: 2000
              })
            })
        })
        .catch(() => {
        })
    },

    handleAddNodeClick (item, node, self) {
      this.editCheckId = item.id
      this.$refs.deptAddTree.setCheckedKeys([item.id])
    },

    addCheckChange (item, node, self) {
      if (node === true) {
        this.editCheckId = item.id
        this.$refs.deptAddTree.setCheckedKeys([item.id])
      } else {
        if (this.editCheckId === item.id) {
          this.$refs.deptAddTree.setCheckedKeys([item.id])
        }
      }
    },
    setTreeDeptId (keys) {
      this.$refs.deptAddTree.setCheckedKeys(keys, true)
    },

    beforeAvatarUpload (file) {
      let that = this
      const extension = file.name.split('.')[1] === 'jpg'
      const extension2 = file.name.split('.')[1] === 'jpeg'
      const extension3 = file.name.split('.')[1] === 'png'
      const extension4 = file.name.split('.')[1] === 'gif'

      const isLt2M = file.size / 1024 / 1024 < 5

      if (!extension && !extension2 && !extension3 && !extension4) {
        console.log('上传模板只能是 jpg/jpeg/png/gif 格式!')
        return false
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 5MB!')
        return false
      }

      let fd = new FormData()
      fd.append('file', file)
      FILE_API.uploadFile(fd).then(function (result) {
        if (result && parseInt(result.code) === 0) {
          that.addImageUrl = result.fileUrl
          that.addForm.avatar = that.addImageUrl
          that.$message.success({
            showClose: true,
            message: '上传成功',
            duration: 2000
          })
        } else {
          that.$message.error({
            showClose: true,
            message: '上传失败',
            duration: 2000
          })
        }
      })
      return false
    },

    handleAvatarSuccess (res, file) {
      console.log(res)
    },
    editBeforeAvatarUpload (file) {
      let that = this
      const extension = file.name.split('.')[1] === 'jpg'
      const extension2 = file.name.split('.')[1] === 'jpeg'
      const extension3 = file.name.split('.')[1] === 'png'
      const extension4 = file.name.split('.')[1] === 'gif'
      const isLt2M = file.size / 1024 / 1024 < 5

      if (!extension && !extension2 && !extension3 && !extension4) {
        console.log('上传模板只能是 jpg/jpeg/png/gif 格式!')
        return false
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 5MB!')
        return false
      }

      let fd = new FormData()
      fd.append('file', file)
      FILE_API.uploadFile(fd).then(function (result) {
        if (result && parseInt(result.code) === 0) {
          that.editForm.avatar = result.fileUrl
          that.$message.success({
            showClose: true,
            message: '上传成功',
            duration: 2000
          })
        } else {
          that.$message.error({
            showClose: true,
            message: '上传失败',
            duration: 2000
          })
        }
      })
      return false
    },

    editHandleAvatarSuccess (res, file) {
      console.log(res)
    }
  },
  mounted () {
    this.handleSearch()
  }
}
</script>

<style scoped>
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
    float: right
  }
</style>
