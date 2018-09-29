<template>
  <div  >
    <el-row >
      <el-col :span="24">
        <div class="grid-content bg-purple-dark">
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
          <el-menu-item index="home" style="left: 20%" v-show="isMenu">主页</el-menu-item>
          <el-menu-item index="course" style="left: 22%" v-show="isMenu">课程</el-menu-item>
          <el-menu-item index="summary" style="left: 24%" v-show="isMenu">课程计划</el-menu-item>
          <el-menu-item index="ask" style="left: 26%" v-show="isMenu">问吧</el-menu-item>

          <div style="float: left; position: absolute; left: 55%; margin-left: 0px; height: 30px; margin-top: 14px">
            <el-input placeholder="搜索课程" v-model="courseName" class="input-with-select" size="small">
              <el-button slot="append" icon="el-icon-search" @click="handleSearchCourse" ></el-button>
            </el-input>
          </div>

          <div class="topInput">
            <div style="float: right;color: #f7f6f6;">
              <el-button style="margin-top: 1.7%;" class="fa fa-user-o"  type="text" @click="dialogFormVisible = true" v-show="isShow">登录</el-button>
              <div style="margin-top: 0.8%;" v-show="isUser">
                <img style="width: 40px;height: 40px ;border-radius:20px" :src="userInfo.avatar"/>&nbsp&nbsp
                <el-dropdown style="float: right;margin-top: 12px" trigger="click" @command="handleCommand">
                  <span class="el-dropdown-link" style="color: #faf7f7;">
                   {{userInfo.nickName}}<i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="user"><i class="fa fa-user" aria-hidden="true"></i>&nbsp;<span style="color: #555;font-size: 14px;">个人信息</span></el-dropdown-item>
                    <el-dropdown-item command="quit"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;退出登录</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </div>
            </div>
          </div>
        </el-menu>
      </div>
      </el-col>
    </el-row>
    <el-dialog title="用户登录" :visible.sync="dialogFormVisible" width="500px">
      <el-form :model="form" :rules="rules">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.username" @keyup.enter.native="tabEnterFun" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.password" @keyup.enter.native="login" auto-complete="off"></el-input>
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
  inject: ['reload'],
  props: {
    activeIndex: {
      type: String,
      default: ''
    },
    isMenu: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      userInfo: {
        nickName: '',
        avatar: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: ' 请输入密码', trigger: 'blur' }
        ]
      },
      data: [],
      router: [],
      dialogFormVisible: false,
      formLabelWidth: '120px',
      isShow: true,
      isUser: false,
      courseName: null,
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    tabEnterFun: function (e) {
      var keyCode = window.event ? e.keyCode : e.which
      if (keyCode === 13) {
        this.$refs.passwordMark.$el.querySelector('input').focus()
      }
    },
    handleSelect (key, keyPath) {
      if (key === 'course') {
        sessionStorage.removeItem('SearchCourseName')
      }
      this.$emit('getActiveIndex', key)
      this.reload()
    },
    handleSearchCourse () {
      if (this.courseName !== null && this.courseName.trim() !== '') {
        sessionStorage.setItem('SearchCourseName', this.courseName.trim())
      } else {
        sessionStorage.removeItem('SearchCourseName')
      }
      this.$router.push('course')
      this.reload()
    },
    login: function () {
      let that = this
      this.$store.dispatch('Login', this.form).then(result => {
        if (result && result.data.code === 0) {
          that.$message.success('登录成功')
          that.res = result
          that.router = that.res.data.router
          that.dialogFormVisible = false
          that.isShow = false
          that.isUser = true
          that.userInfo.nickName = that.res.data.user.nickname
          that.userInfo.avatar = that.res.data.user.avatar
        } else {
          that.$message.error('用户名或密码错误')
          that.dialogFormVisible = false
        }
      }).catch(() => {
        that.$message.error('用户名或密码错误')
        that.dialogFormVisible = false
        that.loading = false
        that.dialogFormVisible = false
      })
    },
    handleCommand (val) {
      if (val === 'quit') {
        this.$confirm('确认退出？').then(_ => {
          let tempuser = JSON.parse(sessionStorage.getItem('access-userinfo'))
          let userId = tempuser.userId
          this.isShow = true
          this.isUser = false
          this.$store.dispatch('Logout', {'userId': userId}).then(res => {
            sessionStorage.removeItem('access-token')
            sessionStorage.removeItem('access-menus')
            sessionStorage.removeItem('access-userinfo')
            this.$router.push('home')
          })
        })
      } else {
        this.$router.push(val)
      }
    }
  },
  mounted: function () {
    let tempuser = null
    tempuser = sessionStorage.getItem('access-userinfo')
    if (tempuser) {
      this.userInfo.nickName = tempuser.nickname
      this.userInfo.avatar = tempuser.avatar
      this.$store.commit('SET_USERID', tempuser.userId)
      this.isShow = false
      this.isUser = true
    } else {
      this.isShow = true
      this.isUser = false
      this.userInfo.nickName = ''
      this.userInfo.avatar = ''
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
