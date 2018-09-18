<template>
  <el-card class="box-card" shadow="never">
    <el-form ref="courseDetailForm" :model="courseDetailForm" :rules="courseCheckRules" label-width="100px"  >
      <el-row>
        <el-col >
          <el-form-item label="课程名称:" prop="course.courseName">
            <el-input v-model="courseDetailForm.course.courseName" placeholder="请输入课程名称" ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :span="8" label="课程分类:" prop="course.courseType">
            <el-select v-model="courseDetailForm.course.courseType" value-key="id" placeholder="请选择分类">
              <el-option  v-for="(item, index) in courseTypeList" :key="item.id" :label="item.typeName" :value="item"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :span="8" label="课程分数:" prop="course.courseScore">
            <el-input v-model="courseDetailForm.course.courseScore" placeholder="课程分数（0-10分）"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :span="8" label="作者:">
            <el-input v-model="courseDetailForm.teacher.nickname" placeholder="" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col >
          <el-form-item label="课程封面:">
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
              type="datetime"
              value-format="yyyy-MM-dd HH:mm:ss"
              format="yyyy-MM-dd HH:mm:ss"
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
        <el-button type="primary" @click="onSubmit">{{submitButtonText}}</el-button>
      </el-form-item>

    </el-form>
  </el-card>
</template>
<script>
  export default {
    data () {
      const validateCourseName = (rule, value, callback) => {
        if (value === null) {
          callback(new Error('课程名称不能为空'))
        } else if (value.trim() === '') {
          callback(new Error('课程名称不能为空字符串'))
        } else {
          callback()
        }
      }
      const validateCourseScore = (rule, value, callback) => {
        if (value === null) {
          callback(new Error('课程分数不能为空'))
        } else if (isNaN(value)) {
          callback(new Error('课程分数必须是0-10的数值'))
        } else if (value < 0 || value > 10) {
          callback(new Error('课程分数不能超过0-10的范围'))
        } else if (value >= 0 && value <= 10) {
          callback()
        } else {
          callback(new Error('课程分数必须是0-10的数值'))
        }
      }
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
            courseScore: null,
            invalidDate: null
          },
          coursewares: [],
          teacher: {
            userId: null,
            nickname: null
          }
        },
        imageUrl: '',
        submitButtonText: null,
        courseCheckRules: {
          course: {
            courseName: [
              {required: true, trigger: 'blur', validator: validateCourseName}
            ],
            courseType: [
              {required: true, trigger: 'change', message: '课程类型不能为空'}
            ],
            courseScore: [
              {required: true, trigger: 'blur', validator: validateCourseScore}
            ]
          }
        }
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        this.$store.dispatch('Get', {'url': '/api-home/course/getCourseTypeList'}).then(res => {
          this.courseTypeList = res.data.re
        })

        let courseId = this.$router.currentRoute.params.courseId
        if (courseId != null) {
          this.submitButtonText = '更新课程'
          this.$store.dispatch('Get', {'url': '/api-home/course/getDetails/' + courseId}).then(res => {
            this.courseDetailForm.course = res.data.re.course
            this.courseDetailForm.coursewares = res.data.re.coursewares
            this.courseDetailForm.teacher.userId = res.data.re.teacher.userId
            this.courseDetailForm.teacher.nickname = res.data.re.teacher.nickname
          })
        } else {
          this.submitButtonText = '发布课程'
          let tempuser = JSON.parse(sessionStorage.getItem('access-userinfo'))
          if (tempuser) {
            this.courseDetailForm.teacher.userId = tempuser.userId
            this.courseDetailForm.teacher.nickname = tempuser.nickname
          }
        }
      })
    },
    methods: {
      handleClick (tab, event) {
        console.log(tab, event)
      },
      onSubmit () {
        this.$refs.courseDetailForm.validate(valid => {
          if (valid) {
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
          } else {
            return false
          }
        })
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
