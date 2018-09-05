<template>
  <div>
  <el-card class="box-card" shadow="never">
    <div slot="header" >
      <span>添加课件</span>
      <el-button style="float: right; padding: 3px 0" type="text" @click="onAddCourseWare">添加</el-button>
    </div>
    <div  class="text item">
      <el-form ref="form" :model="form" label-width="100px"  >
        <el-row>
          <el-col :span="12">
            <el-form-item :span="10" label="课时:" prop="hour">
              <el-input v-model="form.hour" placeholder=""></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :span="12" label="课件名称:" prop="name">
              <el-input v-model="form.name" placeholder="" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="系统课件:" prop="">
              <el-autocomplete
                v-model="state4"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入系统课件名"
                @select="handleSelect"
              ></el-autocomplete>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :span="8" label="上传文件:" prop="fileUrl">
              <el-upload ref="upload"
                action="http://127.0.0.1:8006/file/upload"
                :on-remove="handleRemove"
                :auto-upload="false"
                :limit="1"
                :on-exceed="handleExceed"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                :before-remove="beforeRemove">
                <el-input   style="width: 100%;" readonly><el-button slot="append" icon="el-icon-upload"></el-button></el-input>
              </el-upload>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="分类:">
              <el-select v-model="form.typeName" placeholder="请选择分类" prop="typeName">
                <el-option label="MySql" value="shanghai"></el-option>
                <el-option label="Java" value="beijing"></el-option>
                <el-option label="Spring" value="beijing"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="课件描述:" prop="describe">
          <el-input v-model="form.describe" placeholder="请输入课程描述" type="textarea"
                    :rows="3"></el-input>
        </el-form-item>
      </el-form>
    </div>
  </el-card>
    <el-card class="box-card" shadow="never" style="margin-top: 3px" v-show="isShow" v-for="(item,index) in course.courseWare "  :key="index">
      <el-row >
        <el-col :span="3"><div class="grid-content bg-purple" style="text-align: center">
          <div style="border:1px solid #ebeef5;"><span>第{{index+1}}课</span></div>
        </div></el-col>
        <el-col :span="14"><div class="grid-content bg-purple"style="text-align: center">
          <div style="border: 1px solid red;width: 178px;margin-left: 152px;"><span>{{item.name}}</span></div></div></el-col>
        <el-col :span="7"><div class="grid-content bg-purple"><div style="text-align: right"><i class="el-icon-close"></i></div></div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple" style="text-align: center">
          课件描述：
        </div></el-col>
        <el-col :span="20"><div class="grid-content bg-purple">{{item.describe}}</div></el-col>
        <el-col :span="13"><div class="grid-content bg-purple" style="text-align: center">
        </div></el-col>
        <el-col :span="10"><div class="grid-content bg-purple" style="color:rgb(59, 99, 190); margin-top: 10px;"><span>分类:{{item.typeName}}</span><span style="margin-left: 20px">作者:{{item.author}}</span></div></el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
    export default {
      name: 'addCourse',
      data () {
        return {
          restaurants: [],
          isShow: false,
          state4: '',
          course: {
            courseWare: []
          },
          form: {
            name: '',
            hour: '',
            describe: '',
            typeName: '技术类',
            fileUrl: ''
          }
        }
      },
      mounted: function () {
        this.$nextTick(function () {
          this.course = this.$router.currentRoute.params
          this.courseWareList = this.course.courseWare
          if (this.courseWareList.length) {
            this.isShow = true
          }
          this.restaurants = this.loadAll()
        })
      },
      methods: {
        onSubmit () {
          this.course.courseWare.push(this.form)
          this.$store.dispatch('Post', {'url': `/api-home/course/addCourse`, 'data': this.course}).then(res => {
            this.course = res.data.data
            console.log(this.course)
          })
        },
        onAddCourseWare () {
          console.log(this.$refs.upload)
          this.$refs.upload.submit()
          let data = this.form
          this.course.courseWare.push({'name': data.name, 'describe': data.describe, 'typeName': data.typeName, 'fileUrl': data.fileUrl})
          // this.dataList.push(this.form)
          this.isShow = true
          this.$refs.form.resetFields()
          console.log(this.form)
        },
        handleRemove (file) {
          console.log(file)
        },
        beforeRemove (file, fileList) {
          return this.$confirm(`确定移除 ${file.name}？`)
        },
        beforeAvatarUpload (file) {
          console.log(file)
        },
        handleAvatarSuccess (res, file) {
          console.log(file)
          this.form.fileUrl = res.fileUrl
        },
        handleExceed (files, fileList) {
          this.$message.warning(`已经选择上传文件`)
        },
        loadAll () {
          return [
            {'value': 'Java 课件', 'address': '长宁区新渔路144号'},
            {'value': 'Spring 课件', 'address': '上海市长宁区淞虹路661号'},
            {'value': 'JVM 课件', 'address': '上海市普陀区真北路988号创邑金沙谷6号楼113'}
          ]
        },
        querySearchAsync (queryString, cb) {
          var restaurants = this.restaurants
          var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() => {
            cb(results)
          }, 3000 * Math.random())
        },
        createStateFilter (queryString) {
          return (state) => {
            return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
          }
        },
        handleSelect (item) {
          console.log(item)
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
  .el-card__header{
    padding: 9px 20px;
  }
  .el-card__body {
    padding: 5px;
  }

</style>
