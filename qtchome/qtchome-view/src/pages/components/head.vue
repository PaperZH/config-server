<template>
  <div  >
    <el-row >
      <el-col :span="24"><div class="grid-content bg-purple-dark">
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b" >
          <div style="float: left;position: absolute;margin-top: 17px;margin-left: 15px;">
            <img style="width: 70%;display: block;" src="static/logo.png"/>
          </div>
          <el-menu-item index="home"style="left: 25%" v-show="isMenu">主页</el-menu-item>
          <el-menu-item index="course" style="left: 27%" v-show="isMenu">课程</el-menu-item>
          <el-menu-item index="summary" style="left: 29%" v-show="isMenu">课程计划</el-menu-item>
          <el-menu-item index="ask" style="left: 31%" v-show="isMenu">问吧</el-menu-item>

          <div class="topInput" style="position: relative">
            <div style="min-width: 50%;float: left;margin-left: 23px;height: 30px;margin-top: 5px">
              <el-input  v-model="input" placeholder="请输入内容" suffix-icon="el-icon-search" size="small" v-show="isInput"></el-input>
            </div>
            <div style="float: right;color: #f7f6f6;">
              <el-button style="margin-top: 1.7%;" class="fa fa-user-o"  type="text" @click="dialogFormVisible = true" v-show="isShow">登录</el-button>
              <div style="margin-top: 0.8%;" v-show="isUser">
              <img style="width: 40px;height: 40px ;border-radius:20px" src="static/timg.jpg"/>&nbsp&nbsp
              <el-dropdown style="float: right;margin-top: 12px" trigger="click" @command="handleCommand">
                <span class="el-dropdown-link" style="color: #faf7f7;">
                 葱油饼<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="user">个人中心</el-dropdown-item>
                  <el-dropdown-item command="b">退出</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              </div>
            </div>
          </div>
        </el-menu>
      </div>
      </el-col>
    </el-row>
    <el-dialog title="用户登录" :visible.sync="dialogFormVisible" style="width: 51%;">
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.password" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="login">登 录</el-button>
      </div>
    </el-dialog>
  </div>

</template>
<script>
  export default {
    props: {
      activeIndex: {
        type: String,
        default: ''
      },
      input: {
        type: String,
        default: ''
      },
      isMenu: {
        type: Boolean,
        default: true
      },
      isInput: {
        type: Boolean,
        default: true
      }
    },
    data () {
      return {
        dialogFormVisible: false,
        formLabelWidth: '120px',
        isShow: true,
        isUser: false,
        form: {
          name: '',
          password: ''
        }
      }
    },
    methods: {
      handleSelect (key, keyPath) {
        this.$emit('getActiveIndex', key)
      },
      login: function () {
        console.log(0)
        this.dialogFormVisible = false
        this.isShow = false
        this.isUser = true
        const json = {'url': '/api-home/user/login', 'data': this.form}
        this.$store.dispatch('Post', json).then(res => {
          console.log(res)
        }).catch(() => {
          this.loading = false
        })
      },
      handleCommand (val) {
        this.$router.push(val)
      }
    }
  }
</script>
<style>
 .topInput{
   float: right;
   width: 30%;
   margin-top: 0.5%;
   margin-right: 10%;outline: none;
 }
</style>
