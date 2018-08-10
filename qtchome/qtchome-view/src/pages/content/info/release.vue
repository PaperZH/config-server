<template>
  <el-card class="box-card" shadow="never">
      <el-form ref="form" :model="form" label-width="100px"  >
        <el-row>
          <el-col >
            <el-form-item label="课程名称:">
              <el-input v-model="form.name" placeholder="请输入课程名称" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :span="12" label="课程分类:">
              <el-select v-model="form.region" placeholder="请选择分类">
                <el-option label="MySql" value="shanghai"></el-option>
                <el-option label="Java" value="beijing"></el-option>
                <el-option label="Spring" value="beijing"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :span="12" label="作者:">
              <el-input v-model="form.name" placeholder="" readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col >
            <el-form-item label="上传课程封面:">
              <el-upload
                class="avatar-uploader1"
                :action="action()"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel,.csv,text/plain"
                :before-upload="beforeAvatarUpload">
                <img v-if="imageUrl" :src="imageUrl" class="avatar1">
                <i v-else class="el-icon-plus avatar-uploader-icon1"></i>
              </el-upload>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="课程有效期:">
              <el-input :span="8" v-model="form.name" placeholder="输入天数" >
                <template slot="append">天</template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="课程介绍:">
          <el-input v-model="form.name" placeholder="请输入课程描述" type="textarea"
                    :rows="5"></el-input>
        </el-form-item>
        <el-form-item style="text-align: right;">
          <el-button type="primary" @click="onSubmit">下一步</el-button>
        </el-form-item>

      </el-form>
  </el-card>
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
    mounted: function () {
      this.$nextTick(function () {
        this.form = this.$router.currentRoute.params
      })
    },
    methods: {
      handleClick (tab, event) {
        console.log(tab, event)
      },
      onSubmit () {
        this.$router.push('addCourse')
      },
      handleAvatarSuccess (res, file) {
        this.imageUrl = URL.createObjectURL(file.raw)
      },
      action () {
        return 'ssdads'
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
      },
      handleRemove () {

      }
    }
  }
</script>
<style >
  .avatar-uploader1 .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    margin-left: 0px;
     margin-top: 0px;
  }
  .avatar-uploader1 .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon1 {
    font-size: 28px;
    color: #8c939d;
    width: 300px;
    height: 300px;
    line-height: 300px;
    text-align: center;
  }
  .avatar1 {
    width: 300px;
    height: 300px;
    display: block;
  }

</style>
