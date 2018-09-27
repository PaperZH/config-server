<template>
  <div>
    <el-card class="box-card" shadow="never">
      <div slot="header">
        <span>添加课件</span>
      </div>
      <div class="text item">
        <el-form class="cForm" ref="coursewareForm" :model="coursewareForm" :rules="checkRules"  label-width="100px">
          <el-form-item>
            <el-button style="float: right; padding: 3px 0" type="primary" @click="onAddCourseWare()" >添加</el-button>
          </el-form-item>
          <el-row>
            <el-col :span="12">
              <el-form-item :span="10" label="课时:" prop="hour">
                <el-input v-model="coursewareForm.hour" placeholder=""></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :span="12" label="课件名称:" prop="name">
                <el-input v-model="coursewareForm.name" placeholder=""></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="系统课件:" prop="">
                <el-autocomplete ref="sys"
                                 popper-class="my-autocomplete"
                                 v-model="state4"
                                 :fetch-suggestions="querySearchAsync"
                                 placeholder="请输入系统课件名"
                                 @select="handleSelect">
                  <template slot-scope="{ item }">
                    <div class="name">课件名称：{{ item.value=item.coursewareName }}</div>
                    <span class="addr">课件类型：{{item.typeName}}</span>
                  </template>
                </el-autocomplete>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item :span="8" label="上传文件:" prop="fileUrl"
                            v-loading = "isUploading"
                            element-loading-text="文件上传中"    >
                <el-upload ref="upload"
                           :disabled="uploadIsDisabled"
                           action=""
                           :on-remove="handleRemove"
                           :auto-upload="true"
                           :limit="1"
                           accept="application/pdf,application/vnd.openxmlformats-officedocument.presentationml.presentation,application
                           /vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/msword"
                           :on-exceed="handleExceed"
                           :on-success="handleAvatarSuccess"
                           :before-upload="beforeAvatarUpload"
                           :before-remove="beforeRemove">
                  <el-input style="width: 100%;" readonly>
                    <el-button slot="append" icon="el-icon-upload"></el-button>
                  </el-input>
                </el-upload>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="分类:">
                <el-select v-model="coursewareForm.typeName" value-key="id" placeholder="请选择分类" prop="typeName"
                           @change="handleChange">
                  <el-option
                    ref="coursewareTypeOption"
                    v-for="item in coursewareTypeOptions"
                    :key="item.id"
                    :label="item.typeName"
                    :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="课件描述:" prop="describe">
            <el-input v-model="coursewareForm.describe" placeholder="请输入课程描述"
                      type="textarea" :rows="3"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="box-card" shadow="hover" style="margin-top: 10px"  v-show="isShow"
             v-for="(item,index) in courseWareTable " :key="index">
      <el-row>
        <el-col :span="3">
          <div class="grid-content " style="text-align: center">
            <div ><span>第{{index+1}}课</span></div>
          </div>
        </el-col>

        <el-col :span="6">
          <div class="grid-content " style="text-align: left">
            <div style="color:rgb(59, 99, 190); "><span>课件名称: {{item.name}}</span></div>
            <!--border: 1px solid red;width: 178px;margin-left: 102px;-->
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content " style="color:rgb(59, 99, 190); "><span>课程分类: {{item.typeName}}</span>
            </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content"></div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content" >
            <el-button type="primary" @click="handleStudyClick(scope.row)" size="small">查看</el-button>
          </div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content" >
            <el-button type="primary" size="small">
              <a :href="scope.row.sourceUrl" style="text-decoration: none; color: #fff;" download>下载</a>
              </el-button>
          </div>
        </el-col>
        <el-col :span="1">
          <div class="grid-content ">
            <div style="text-align: right"><i class="el-icon-close"></i></div>
          </div>
        </el-col>
        <el-col :span="3">
          <div class="grid-content " style="text-align: center">
            课件描述：
          </div>
        </el-col>
        <el-col :span="20">
          <!--<el-input-->
            <!--type="textarea"-->
            <!--:autosize="{ minRows: 2, maxRows: 4}"-->
            <!--v-model="textarea3">-->
          <!--</el-input>-->
          <div class="grid-content " style="border: 1px solid #ebeef5;">{{item.describe}}</div>
        </el-col>
      </el-row>
    </el-card>
    <div>
      <el-dialog id="studyDialog" title="" :visible.sync="studyDialogVisible" width="100%">
        <iframe :src="previewUrl" style="width:100%; height: 600px"></iframe>
      </el-dialog>
    </div>
  </div>
</template>
<script>
  import Drawupplan from './drawupplan'
  export default {
    components: {Drawupplan},
    inject: ['reload'],
    name: 'addCourse',
    data () {
      const validateCoursewareName = (rule, value, callback) => {
        if (value === null) {
          callback(new Error('课件名称不能为空'))
        } else if (value.trim() === '') {
          callback(new Error('课件名称不能为空'))
        } else {
          callback()
        }
      }
      return {
        studyDialogVisible: false,
        previewUrl: null,
        uploadIsDisabled: false,
        isShow: false,
        state4: '',
        addflag: '',
        isUploading: false,
        courseWareTable: [],
        coursewareTypeOptions: [],
        courseWareList: [],
        coursewareForm: {
          hour: '',
          name: '',
          typeId: '',
          typeName: '',
          describe: '',
          flag: '',
          baseCoursewareId: '',
          courseId: ''
        },
        checkRules: {
          name: [
              {required: true, trigger: 'blur', validator: validateCoursewareName}
          ],
          hour: [
              {required: true, trigger: 'blur', message: '课时不能为空'}
          ]
        }
      }
    },
    mounted () {
      // 得到课程的id
      this.coursewareForm.courseId = this.$router.currentRoute.params.course.courseId
      // 得到课程的课件的集合
      var mycars = []
      mycars = this.$router.currentRoute.params.coursewares
      console.log('mycars')
      console.log(mycars)
      for (var i = 0; i < mycars.length; i++) {
        var courseWareTableItem = {}
        courseWareTableItem.name = mycars[i].name
        courseWareTableItem.describe = mycars[i].description
        courseWareTableItem.typeName = mycars[i].type
        this.courseWareTable.push(courseWareTableItem)
      }
      if (this.courseWareTable.length > 0) {
        this.isShow = true
      }
      this.loadAll()
    },
    methods: {
      onAddCourseWare () {
        this.$refs.coursewareForm.validate(valid => {
          if (valid) {
            this.$store.dispatch('Post', {
              'url': '/api-home/courseCourseware/addCourseCourseware',
              'data': this.coursewareForm
            }).then(fileRes => {
              this.addflag = fileRes.data.re
              if (this.addflag === 1) {
                console.log(this.coursewareForm)
                var courseWareTableItem = {}
                courseWareTableItem.name = this.coursewareForm.name
                courseWareTableItem.describe = this.coursewareForm.describe
                courseWareTableItem.typeName = this.coursewareForm.typeName
                this.courseWareTable.push(courseWareTableItem)
                this.addSuccessfully()
              } else {

              }
              console.log('fileRes.data.re:' + fileRes.data.re)
            })
          }
        })
      },
      handleRemove (file) {
        console.log(file)
      },
      beforeRemove (file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`)
      },
      beforeAvatarUpload (file) {
        this.$refs.sys.disabled = true
        this.isUploading = true
        // this.uploadLoading()
        let tem = new FormData()
        tem.append('file', file)
        this.$store.dispatch('Post', {'url': `/api-home/courseware/upLoad`, 'data': tem}).then(fileRes => {
          console.log(fileRes.data.re)
          if (fileRes.data.re != null) {
            this.coursewareForm.baseCoursewareId = fileRes.data.re
            this.isUploading = false
            this.uploadSuccessfully()
            this.coursewareForm.flag = 1
          } else {
            this.isUploading = false
            this.uploadfailed()
          }
        })
        console.log(file)
      },
      handleAvatarSuccess (response, file, fileList) {
        console.log('handleAvatarSuccess')
        console.log(response)
        console.log(file)
      },
      handleExceed (files, fileList) {
        console.log(this.$refs.upload)
        this.$message.warning(`已经选择上传文件`)
      },
      loadAll () {
        this.$store.dispatch('Post', {
          'url': `/api-home/courseware/getAllBaseCoursewares`,
          'data': ''
        }).then(coursewareListRes => {
          this.courseWareList = coursewareListRes.data.re
        })
        this.$store.dispatch('Post', {
          'url': `/api-home/courseware/getAllTypes`,
          'data': ''
        }).then(coursewareTypeListRes => {
          this.coursewareTypeOptions = coursewareTypeListRes.data.re
        })
      },
      querySearchAsync (queryString, cb) {
        var courseWareList = this.courseWareList
        var results = queryString ? courseWareList.filter(this.createStateFilter(queryString)) : courseWareList
        cb(results)
      },
      createStateFilter (queryString) {
        return (courseWare) => {
          return (courseWare.coursewareName.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
        }
      },
      handleSelect (item) {
        this.coursewareForm.baseCoursewareId = item.id
        this.uploadIsDisabled = true
      },

      handleChange (item) {
        this.coursewareForm.typeName = item.typeName
        this.coursewareForm.typeId = item.id
      },
      uploadSuccessfully () {
        this.$alert('上传成功', '提示', {
          confirmButtonText: '确定'
        })
      },
      addSuccessfully () {
        this.$alert('课件添加成功', '提示', {
          confirmButtonText: '确定',
          callback: action => {
            console.log('addSuccessfully')
            this.isShow = true
            this.coursewareForm.baseCoursewareId = ''
            this.coursewareForm.describe = ''
            this.coursewareForm.flag = ''
            this.coursewareForm.hour = ''
            this.coursewareForm.name = ''
            this.state4 = ''
            console.log(this.courseWareTable)
          }
        })
      },
      uploadfailed () {
        this.$alert('课件添加失败，请重试', '提示', {
          confirmButtonText: '确定'
        })
      },

      uploadLoading () {
        const loading = this.$loading({
          // target: cForm,
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        })
        setTimeout(() => {
          loading.close()
        }, 2000)
      },
      handleStudyClick (row) {
        this.$store.dispatch('Post', {'url': '/api-home/course/addCourseReadNum/' + this.course.courseId}).then(res => {
          if (res.data.success === true) {
            this.previewUrl = row.preUrl
            this.studyDialogVisible = true
          }
        })
      }

    }

  }
</script>

<style>
  ul.el-upload-list.el-upload-list--text {
    margin: -46px -1px;
    height: 37px;
    width: 50%;
  }

  li.el-upload-list__item.is-ready {
    width: 105px;
    float: left;
    margin: 8px;

  }

  .el-card__header {
    padding: 9px 20px;
  }

  .el-card__body {
    padding: 5px;
  }

  /*.my-autocomplete {*/
  /*line-height: normal;*/
  /*padding: 7px;*/
  /*}*/

  .name {
    text-overflow: ellipsis;
    overflow: hidden;
  }

  .addr {
    font-size: 12px;
    color: #b4b4b4;
  }

  .highlighted .addr {
    color: #ddd;
  }

</style>
