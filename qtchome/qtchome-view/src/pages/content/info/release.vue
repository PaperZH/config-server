<template>
  <el-card class="box-card" shadow="never">
    <el-form ref="courseDetailForm" :model="courseDetailForm" label-width="100px"  >
      <el-row>
        <el-col >
          <el-form-item label="课程名称:">
            <el-input v-model="courseDetailForm.course.courseName" placeholder="请输入课程名称" ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :span="8" label="课程分类:">
            <el-select v-model="courseDetailForm.course.courseType" value-key="id" placeholder="请选择分类" >
              <div  v-for="(item, index) in courseTypeList">
                <el-option  :key="item.id" :label="item.typeName" :value="item"></el-option>
              </div>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :span="8" label="总分:">
            <el-input v-model="courseDetailForm.course.courseScore" placeholder=""></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :span="8" label="作者:">
            <el-input v-model="courseDetailForm.teacher.username" placeholder="" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col >
          <el-form-item label="上传课程封面:">
            <el-upload
              class="avatar-uploader1"
              action=""
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel,.csv,text/plain"
              :before-upload="beforeAvatarUpload">
              <img v-if="courseDetailForm.course.courseCover" :src="courseDetailForm.course.courseCover" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon1"></i>
            </el-upload>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="课程有效期:">
            <el-date-picker
              v-model="courseDetailForm.course.invalidDate"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="课程介绍:">
        <el-input v-model="courseDetailForm.course.courseDescription" placeholder="请输入课程描述" type="textarea"
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
        courseTypeList: [{
          id: '',
          typeName: ''
        }],
        courseDetailForm: {
          course: {
            courseId: null,
            typeName: null,
            courseType: null,
            courseName: null,
            courseCover: null,
            courseDescription: '暂无描述',
            courseScore: '10',
            invalidDate: null
          },
          coursewares: [],
          teacher: {
            userId: 100,
            username: '张三'
          }
          // teacher: {}
        },
        imageUrl: ''

      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.$store.dispatch('Get', {'url': '/api-home/course/getCourseTypeList'}).then(res => {
          this.courseTypeList = res.data.re
        })
        let courseId = this.$router.currentRoute.params.courseId
        if (courseId != null) {
          this.$store.dispatch('Get', {'url': '/api-home/course/getDetails/' + courseId}).then(res => {
            this.courseDetailForm.course = res.data.re.course
            this.courseDetailForm.coursewares = res.data.re.coursewares
          })
        } else {
          console.log('创建新的course')
        }
      })
    },
    methods: {
      handleClick (tab, event) {
        console.log(tab, event)
      },
      onSubmit () {
        // 如果courseId存在，则是修改课程信息，否则是新建课程信息
        let data = {'userId': this.courseDetailForm.teacher.userId, 'course': this.courseDetailForm.course}
        if (this.courseDetailForm.course.courseId != null) {
          this.$store.dispatch('Post', {'url': '/api-home/course/updateUserCourse', 'data': data}).then(res => {
            this.$router.push({name: 'addCourse', params: this.courseDetailForm})
          })
        } else {
          this.$store.dispatch('Post', {'url': '/api-home/course/addUserCourse', 'data': data}).then(res => {
            this.courseDetailForm.course.courseId = res.data.re.courseId
            this.$router.push({name: 'addCourse', params: this.courseDetailForm})
          })
        }
      },
      handleAvatarSuccess (res, file) {
        console.log(res)
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
        let fd = new FormData()
        fd.append('file', file)
        this.$store.dispatch('Post', {'url': '/api-home/course/file/upload', 'data': fd}).then(res => {
          this.courseDetailForm.course.courseCover = res.data.fileUrl
        })
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
