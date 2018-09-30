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
                <label style="letter-spacing:5px;">第{{coursewareForm.hour}}课时</label>
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
                                 :disabled="selectIsDisabled"
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
    <div style="margin-top: 10px">
      <el-table
        :data="courseWareTable"
        border
        style="width: 100%">
        <el-table-column
          prop="coursewareNum"
          label="课时"
          width="50">
        </el-table-column>
        <el-table-column
          prop="coursewareName"
          label="课件名称"
          width="80">
        </el-table-column>
        <el-table-column
          prop="coursewareTypeName"
          label="课件分类 "
          width="80"
        >
        </el-table-column>
        <el-table-column
          prop="publishTime"
          label="发布时间 "
          width="100"
        >
        </el-table-column>
        <el-table-column
          prop="coursewareDescription"
          label="课件描述"
        >
        </el-table-column>
        <el-table-column label="操作" width="200px">
          <template slot-scope="scope">
            <el-button
              @click="scanClick(scope.row)"
              size="mini"
              type="info"
            >查看</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="downloadClick(scope.row)"
            >
              下载
              </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block" style="text-align: center">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="1"
          :page-sizes="[100, 200, 300, 400]"
          :page-size="5"
          layout="total, prev, pager, next, jumper"
          :total=coursewareTotal>
        </el-pagination>
      </div>
    </div>
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
        coursewareQury: {
          pageNo: '1',
          pageSize: '5',
          courseId: ''
        },
        url: 'http://127.0.0.1:8002/api-home/',
        coursewareTotal: 0,
        studyDialogVisible: false,
        previewUrl: null,
        uploadIsDisabled: false,
        selectIsDisabled: false,
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
          courseId: '',
          sourceUrl: '',
          preUrl: ''
        },
        checkRules: {
          name: [
              {required: true, trigger: 'blur', validator: validateCoursewareName}
          ]
          // hour: [
          //     {required: true, trigger: 'blur', message: '课时不能为空'}
          // ]
        }
      }
    },
    mounted () {
      // 得到课程的id
      this.coursewareForm.courseId = this.$router.currentRoute.params.course.courseId
      this.coursewareQury.courseId = this.$router.currentRoute.params.course.courseId
      this.loadAll()
      this.getCoursewareList()
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
                this.getCoursewareList()
                this.addSuccessfully()
                this.coursewareForm.baseCoursewareId = ''
                this.coursewareForm.describe = ''
                this.coursewareForm.flag = ''
                this.coursewareForm.name = ''
                this.state4 = ''
                this.uploadIsDisabled = false
                this.selectIsDisabled = false
              } else {

              }
              console.log('fileRes.data.re:' + fileRes.data.re)
            })
          }
        })
      },
      handleRemove (file) {
      },
      beforeRemove (file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`)
      },
      beforeAvatarUpload (file) {
        this.isUploading = true
        // this.uploadLoading()
        let tem = new FormData()
        tem.append('file', file)
        this.$store.dispatch('Post', {'url': `/api-home/courseware/upLoad`, 'data': tem}).then(fileRes => {
          if (fileRes.data.re != null) {
            this.selectIsDisabled = true
            this.coursewareForm.baseCoursewareId = fileRes.data.re
            this.isUploading = false
            this.uploadSuccessfully()
            this.coursewareForm.flag = 1
          } else {
            this.isUploading = false
            this.uploadfailed()
          }
        })
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
          'url': `/api-home/courseware/frontPage/getAllBaseCoursewares`,
          'data': ''
        }).then(coursewareListRes => {
          this.courseWareList = coursewareListRes.data.re
        })
        this.$store.dispatch('Post', {
          'url': `/api-home/courseware/frontPage/getAllTypes`,
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
        this.coursewareForm.preUrl = item.preUrl
        this.coursewareForm.sourceUrl = item.sourceUrl
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
            this.coursewareForm.baseCoursewareId = ''
            this.coursewareForm.describe = ''
            this.coursewareForm.flag = ''
            this.coursewareForm.name = ''
            this.state4 = ''
            this.uploadIsDisabled = false
            this.selectIsDisabled = false
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
      scanClick (item) {
        console.log(item)
        this.$store.dispatch('Post', {
          'url': `/api-home/courseware/frontPage/scanCourseware`,
          'data': item.baseCoursewareId
        }).then(coursewareRes => {
          console.log(coursewareRes.data.re)
          if (typeof (coursewareRes.data.re) !== 'undefined') {
            this.previewUrl = coursewareRes.data.re
            this.studyDialogVisible = true
          } else {
            this.$alert('课件暂不支持查看，请稍后重试', '提示', {
              confirmButtonText: '确定'
            })
          }
        })
      },
      handleCurrentChange (val) {
        // this.loading = true
        this.coursewareQury.pageNo = val
        this.getCoursewareList()
      },
      getCoursewareList () {
        this.$store.dispatch('Post', {'url': '/api-home/courseCourseware/getAddCoursewarePageList', 'data': this.coursewareQury}).then(res => {
          this.loading = false
          if (res.data.success) {
            this.courseWareTable = res.data.re.addCoursewareListDTO
            this.coursewareTotal = res.data.re.count
            this.sethour()
          }
        }).catch(error => {
          console.log(error)
        })
      },
      sethour () {
        this.coursewareForm.hour = this.coursewareTotal + 1
      },
      downloadClick (item) {
        console.log(item.sourceUrl)
        window.location.href = 'http://127.0.0.1:8002/api-home/courseware/frontPage/downLoadCourseware?fileName=' + item.coursewareName + '&sourceUrl=' + item.sourceUrl
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
