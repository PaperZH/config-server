<template>
  <el-row class="warp">
    <el-col :span="24" class="warp-breadcrum">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
        <el-breadcrumb-item>角色管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>

    <el-col :span="24" class="warp-main" v-loading="loading" element-loading-text="拼命加载中">
      <!--工具条-->
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form :inline="true" :model="filters">
          <el-form-item>
            <el-input v-model="filters.roleName" placeholder="角色名" @keyup.enter.native="handleSearch"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-on:click="handleSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="showAddDialog">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <!--列表-->
      <el-table :data="role" border highlight-current-row @selection-change="selsChange"
                style="width: 100%;">
        <!-- <el-table-column type="selection" width="55"></el-table-column> -->
        <el-table-column type="index" width="60"></el-table-column>
        <el-table-column prop="roleName" label="角色名" align="center" sortable></el-table-column>
        <el-table-column prop="roleDesc" label="角色描述" align="center" sortable></el-table-column>
        <el-table-column prop="remark" label="备注" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="150">
          <template slot-scope="scope">
            <el-button size="mini" @click="showEditDialog(scope.$index,scope.row)">编辑</el-button>
            <el-button type="danger" @click="removeRole(scope.$index,scope.row)" size="mini">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--工具条-->
      <el-col :span="24" class="toolbar" >
        <el-pagination layout="total,prev,sizes, pager, next,jumper" background
              @size-change="handleSizeChange"  
              @current-change="handleCurrentChange" 
              :page-size="limit"
              :total="total"
              :page-sizes="[10, 20, 30]">
        </el-pagination>
      </el-col>

      <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
        <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">
          <el-form-item label="角色名" prop="roleName">
            <el-input v-model="editForm.roleName" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDesc">
            <el-input v-model="editForm.roleDesc" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" v-model="editForm.remark" :rows="2"></el-input>
          </el-form-item>
          <el-form-item label="权限">
            <el-tree
              :data="menus"
              show-checkbox
              node-key="id"
              :props="treeProps"
              ref="menuAddTree">
            </el-tree>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="editFormVisible = false">取消</el-button>
          <el-button type="primary" @click.native="editSubmit">提交</el-button>
        </div>
      </el-dialog>

      <!--新增界面-->
      <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
          <el-form-item label="角色名" prop="roleName">
            <el-input v-model="addForm.roleName" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDesc">
            <el-input v-model="addForm.roleDesc" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" v-model="addForm.remark" :rows="2"></el-input>
          </el-form-item>
          <el-form-item label="权限">
            <el-tree
              :data="menus"
              show-checkbox
              node-key="id"
              :props="treeProps"
              ref="menuAddTree">
            </el-tree>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="addFormVisible = false">取消</el-button>
          <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
        </div>
      </el-dialog>

    </el-col>
  </el-row>
</template>
<script>
  import util from '../../common/util'
  import API from '../../api/api_role';
  import MENU_API from '../../api/api_menu';

  export default {
    data() {
      return {
        filters: {
          roleName: ''
        },
        role: [],
        total: 0,
        page: 1,
        limit: 10,
        loading: false,
        sels: [], //列表选中列
        menus: [],
        menuIds: [],//角色拥有的权限
        treeProps: {
          children: 'children',
          label: 'text'
        },

        //编辑相关数据
        editFormVisible: false,//编辑界面是否显示
        editFormRules: {
          roleName: [
            {required: true, message: '请输入角色名', trigger: 'blur'}
          ],  
          roleDesc: [
            {required: true, message: '请输入角色描述', trigger: 'blur'}
          ]
        },
        editForm: {
          id: 0,
          roleName: '',
          roleDesc: '',
          remark: ''
        },

        //新增相关数据
        addFormVisible: false,//新增界面是否显示
        addLoading: false,
        addFormRules: {
          roleName: [
            {required: true, message: '请输入角色名', trigger: 'blur'}
          ],  
          roleDesc: [
            {required: true, message: '请输入角色描述', trigger: 'blur'}
          ]
        },
        addForm: {
          roleName: '',
          roleDesc: '',
          remark: ''
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
          limit: 10,
          roleName: that.filters.roleName
        };

        that.loading = true;
        API.findList(params).then(function (result) {
          that.loading = false;
          if (result && result.rows) {
            that.total = result.total;
            that.role = result.rows;
          }
        }, function (err) {
          that.loading = false;
          that.$message.error({showClose: true, message: err.toString(), duration: 2000});
        }).catch(function (error) {
          that.loading = false;
          console.log(error);
          that.$message.error({showClose: true, message: '请求出现异常', duration: 2000});
        });
      },
      selsChange: function (sels) {
        this.sels = sels;
      },
      //删除
      removeRole: function (index, row) {
        let that = this;
        this.$confirm('确认删除该记录吗?', '提示', {type: 'warning'}).then(() => {
          that.loading = true;
          API.removeRole({id: row.roleId}) 
          .then(
                function (result) {
                  that.loading = false;
                  if (result && parseInt(result.code) === 0) {
                    that.$message.success({
                      showClose: true,
                      message: "删除成功",
                      duration: 1500
                    });
                    that.search();
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
          })
          .catch(() => {
          });
      },
      //显示编辑界面
      showEditDialog: function (index, row) {
        this.editFormVisible = true;
        this.editForm = Object.assign({}, row);
        let that = this
        MENU_API.menuIdsByRoleId({roleId: row.roleId}).then(function (res) {
          that.setMenuIds(res)
          that.$nextTick(function () {
            MENU_API.menus('').then(function (result) {
              that.menus = result
            })
          })
        })
      },
      //编辑
      editSubmit: function () {
        let that = this;
        this.$refs.editForm.validate((valid) => {
          if (valid) {
            this.loading = true;
            let para = Object.assign({}, this.editForm);
            para.menuIds = that.getMenuIds()
            API.update(para.id, para).then(function (result) {
              that.loading = false;
              if (result && parseInt(result.code) === 0) {
                that.$message.success({showClose: true, message: '修改成功', duration: 2000});
                that.$refs['editForm'].resetFields();
                that.editFormVisible = false;
                that.search();
              } else {
                that.$message.error({showClose: true, message: '修改失败', duration: 2000});
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
      showAddDialog: function () {
        this.addFormVisible = true;
        this.addForm = {};
        let that = this
        MENU_API.menus('').then(function (result) {
          that.menus = result
        })

      },
      //新增
      addSubmit: function () {
        let that = this;
        this.$refs.addForm.validate((valid) => {
          if (valid) {
            that.loading = true;
            let para = Object.assign({}, this.addForm);
            para.menuIds = that.getMenuIds()
            API.add(para).then(function (result) {
              that.loading = false;
              if (result && parseInt(result.code) === 0) {
                that.$message.success({showClose: true, message: '新增成功', duration: 2000});
                that.$refs['addForm'].resetFields();
                that.addFormVisible = false;
                that.search();
              } else {
                that.$message.error({showClose: true, message: '修改失败', duration: 2000});
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
      //批量删除
      batchDeleteBook: function () {
        let ids = this.sels.map(item => item.id).toString();
        let that = this;
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          that.loading = true;
          API.removeBatch(ids).then(function (result) {
            that.loading = false;
            if (result && parseInt(result.errcode) === 0) {
              that.$message.success({showClose: true, message: '删除成功', duration: 1500});
              that.search();
            }
          }, function (err) {
            that.loading = false;
            that.$message.error({showClose: true, message: err.toString(), duration: 2000});
          }).catch(function (error) {
            that.loading = false;
            console.log(error);
            that.$message.error({showClose: true, message: '请求出现异常', duration: 2000});
          });
        }).catch(() => {

        });
      },
      //获取选中、半选中节点
      getMenuIds: function () {
        return this.$refs.menuAddTree.getCheckedKeys().concat(this.$refs.menuAddTree.getHalfCheckedKeys())
      },
      /**
       * (keys, leafOnly) 接收两个参数，1. 勾选节点的 key 的数组 2. boolean 类型的参数，若为 true 则仅设置叶子节点的选中状态，默认值为 false
       */
      setMenuIds: function (keys) {
        this.$refs.menuAddTree.setCheckedKeys(keys, true)
      }
    },
    mounted() {
      this.handleSearch()

    }
  }
</script>

<style scoped>
  .el-pagination {
    margin-top: 15px;
    margin-bottom: 15px;
    float: right
  }
</style>
