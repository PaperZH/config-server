<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="我的资料" name="first" >
      <el-form ref="form" :model="form" label-width="60px" label-position="left"  >
        <el-row>
          <el-col :span="16">
            <el-form-item label="用户名:">
              <el-input v-model="form.name" placeholder="请输入用户名" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
          <el-form-item :span="16" label="作者:">
            <el-input v-model="form.name" placeholder="请输入作者"></el-input>
          </el-form-item>
          </el-col>

            <label style="margin-left: 37px;">头像：</label>
            <el-upload
              class="avatar-uploader"
              action="/api/img"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel,.csv,text/plain"

              :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
        </el-row>


        <el-form-item label="邮箱:">
          <el-input v-model="form.name" placeholder="请输入邮箱"></el-input>
        </el-form-item>

        <el-form-item style="text-align: right;">
          <el-button type="primary" @click="onSubmit">保存</el-button>
          <el-button>取消</el-button>
        </el-form-item>

      </el-form>

    </el-tab-pane>

  </el-tabs>
</template>
<script>
  export default {
    data () {
      return {
        activeName: 'first',
        form: {
          name: ''
        },
        imageUrl: ''

      }
    },
    methods: {
      handleClick (tab, event) {
        console.log(tab, event)
      },
      onSubmit () {},
      handleAvatarSuccess (res, file) {
        this.imageUrl = URL.createObjectURL(file.raw)
      },
      beforeAvatarUpload (file) {
        const isJPG = file.type === 'image/jpeg'
        const isLt2M = file.size / 1024 / 1024 < 2

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!')
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!')
        }
        return isJPG && isLt2M
      }
    }
  }
</script>
<style >
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    margin-left: 91px;
    margin-top: -19px;

  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 110px;
    height: 110px;
    line-height: 110px;
    text-align: center;
  }
  .avatar {
    width: 110px;
    height: 110px;
    display: block;
  }
</style>
