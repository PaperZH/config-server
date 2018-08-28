<template>
  <el-row class="warp">
    <el-col :span="24" class="warp-breadcrum" :loading="loading">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
        <el-breadcrumb-item>个人信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>

    <el-col :span="24" class="warp-main">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="form.useranme" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="工号">
          <el-input v-model="form.userno" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" disabled></el-input>
        </el-form-item>
        <el-form-item prop="nickname" label="昵称">
          <el-input v-model="form.nickname"></el-input>
        </el-form-item>
        <el-form-item label="上传图片" prop="avatar">
          <el-input v-model="form.avatar" auto-complete="off"></el-input>
          <el-upload
            class="avatar-uploader"
            action=""
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <div slot="tip" class="el-upload__tip" >上传模板只能是 jpg/jpeg/png/gif 格式，且不超过5MB</div>
            <img v-if="form.avatar" :src="form.avatar" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="warning" @click="handleSaveProfile">修改并保存</el-button>
          <el-button type="success" @click="$router.back(-1)">《《《 返回</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
import API from '../../api/api_user'
import FILE_API from '../../api/api_file'

export default {
  data () {
    return {
      loading: false,
      form: {
        useranme: '',
        nickname: '',
        name: '',
        userno: '',
        email: '',
        avatar: '',
        userId: ''
      },
      rules: {
        nickname: [
          {required: true, message: '请输入昵称', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    handleSaveProfile () {
      let that = this
      that.$refs.form.validate((valid) => {
        if (valid) {
          that.loading = true
          let args = {
            userId: that.form.userId,
            nickname: that.form.nickname,
            avatar: that.form.avatar
          }
          API.changeProfile(args).then(function (result) {
            if (result.code === 0) {
              location.reload()
              that.$message.success({
                showClose: true,
                message: '修改成功',
                duration: 2000
              })
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
          that.form.avatar = result.fileUrl
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
    }
  },
  mounted () {
    let that = this
    API.tokenUser().then(function (result) {
      that.form.useranme = result.username
      that.form.nickname = result.nickname
      that.form.email = result.email
      that.form.name = result.name
      that.form.userno = result.userno
      that.form.avatar = result.avatar
      that.form.userId = result.id
    })
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
</style>
