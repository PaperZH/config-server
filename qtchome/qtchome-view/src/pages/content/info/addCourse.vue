<template>
  <div>
    <el-card class="box-card" shadow="never">
      <div slot="header">
        <span>添加课件</span>
      </div>
      <div class="text item">
        <el-form ref="form" :model="form" label-width="100px">
          <el-form-item>
            <el-button style="float: right; padding: 3px 0" type="text" @click="onAddCourseWare()">添加</el-button>
          </el-form-item>
          <el-row>
            <el-col :span="12">
              <el-form-item :span="10" label="课时:" prop="hour">
                <el-input v-model="form.hour" placeholder=""></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :span="12" label="课件名称:" prop="name">
                <el-input v-model="form.name" placeholder=""></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="系统课件:" prop="">
                <el-autocomplete
                  popper-class="my-autocomplete"
                  v-model="state4"
                  :fetch-suggestions="querySearchAsync"
                  placeholder="请输入系统课件名"
                  @select="handleSelect"
                >
                  <template slot-scope="{ item }">
                    <div class="name">课程名称：{{ item.value=item.coursewareName }}</div>
                    <span class="addr">课程类型：{{item.typeName}}</span>
                  </template>
                </el-autocomplete>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item :span="8" label="上传文件:" prop="fileUrl">
                <el-upload ref="upload"
                           action=""
                           :on-remove="handleRemove"
                           :auto-upload="true"
                           :limit="1"
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
                <el-select v-model="form.typeName" placeholder="请选择分类" prop="typeName"
                           @change="handleChange"
                >
                  <el-option
                    v-for="item in coursewareTypeOptions"
                    :key="item.id"
                    :label="item.typeName"
                    :value="item.id"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="课件描述:" prop="describe">
            <el-input v-model="form.describe" placeholder="请输入课程
          描述" type="textarea"
                      :rows="3"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="box-card" shadow="never" style="margin-top: 3px" v-show="isShow"
             v-for="(item,index) in course.courseWare " :key="index">
      <el-row>
        <el-col :span="3">
          <div class="grid-content bg-purple" style="text-align: center">
            <div style="border:1px solid #ebeef5;"><span>第{{index+1}}课</span></div>
          </div>
        </el-col>
        <el-col :span="14">
          <div class="grid-content bg-purple" style="text-align: center">
            <div style="border: 1px solid red;width: 178px;margin-left: 152px;"><span>{{item.name}}</span></div>
          </div>
        </el-col>
        <el-col :span="7">
          <div class="grid-content bg-purple">
            <div style="text-align: right"><i class="el-icon-close"></i></div>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple" style="text-align: center">
            课件描述：
          </div>
        </el-col>
        <el-col :span="20">
          <div class="grid-content bg-purple">{{item.describe}}</div>
        </el-col>
        <el-col :span="13">
          <div class="grid-content bg-purple" style="text-align: center">
          </div>
        </el-col>
        <el-col :span="10">
          <div class="grid-content bg-purple" style="color:rgb(59, 99, 190); margin-top: 10px;"><span>分类:{{item.typeName}}</span><span
            style="margin-left: 20px">作者:{{item.author}}</span></div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>
<script>
  export default {
    name: 'addCourse',
    data () {
      return {
        isShow: false,
        state4: '',
        course: {
          courseWare:
            {}

        },

        coursewareTypeOptions: [],

        courseWareList: [],

        form: {
          hour: '',
          name: '',
          typeId: '',
          describe: '',
          fileUrl: '',
          baseCoursewareId: '',
          courseId: '2'

        }
      }
    },
    mounted: function () {
      this.loadAll()
      console.log(this.courseWareList)
      this.courseId = this.$router.currentRoute.params.courseId
    },
    methods: {
      onAddCourseWare () {
        console.log(this.form)
        this.$store.dispatch('Post', {
          'url': `/api-home/courseCourseware/addCourseCourseware`,
          'data': this.form
        }).then(fileRes => {
          console.log(fileRes.data.re)
        })
      },
      handleRemove (file) {
        console.log(file)
      },
      beforeRemove (file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`)
      },
      beforeAvatarUpload (file) {
        let tem = new FormData()

        tem.append('file', file)
        this.$store.dispatch('Post', {'url': `/api-home/courseware/save`, 'data': tem}).then(fileRes => {
          console.log(fileRes.data.re)
          this.form.fileUrl = fileRes.data.re
        })
        console.log(file)
      },
      handleAvatarSuccess (res, file) {
        console.log(file)
        this.form.fileUrl = res.fileUrl
      },
      handleExceed (files, fileList) {
        console.log(this.$refs.upload)
        this.$message.warning(`已经选择上传文件`)
      },
      loadAll () {
        console.log('---------------------loadAll----------------------------')
        this.$store.dispatch('Post', {
          'url': `/api-home/courseware/getAllBaseCoursewares`,
          'data': ''
        }).then(coursewareListRes => {
          this.courseWareList = coursewareListRes.data.re
          console.log(this.courseWareList)
        })
        this.$store.dispatch('Post', {
          'url': `/api-home/courseware/getAllTypes`,
          'data': ''
        }).then(coursewareTypeListRes => {
          this.coursewareTypeOptions = coursewareTypeListRes.data.re
          console.log(coursewareTypeListRes.data.re)
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
        this.form.baseCoursewareId = item.id
      },

      handleChange (item) {
        console.log(item)
        this.form.typeId = item
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
