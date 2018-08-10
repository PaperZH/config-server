<template>
  <div>
  <el-card class="box-card" shadow="never">
    <div slot="header" >
      <span>添加课件</span>
      <el-button style="float: right; padding: 3px 0" type="text" @click="onAddCourse">添加</el-button>
    </div>
    <div  class="text item">
      <el-form ref="form" :model="form" label-width="100px"  >
        <el-row>
          <el-col :span="12">
            <el-form-item :span="10" label="课时:">
              <el-input v-model="form.hour" placeholder=""></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :span="12" label="课件名称:">
              <el-input v-model="form.name" placeholder="" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="系统课件:">
              <el-autocomplete
                v-model="state4"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入系统课件名"
                @select="handleSelect"
              ></el-autocomplete>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :span="8" label="上传文件:">
              <el-upload
                action="https://jsonplaceholder.typicode.com/posts/"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :file-list="form.fileList2"
                :auto-upload="false">
                <el-input   style="width: 100%;" readonly><el-button slot="append" icon="el-icon-upload"></el-button></el-input>
              </el-upload>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="分类:">
              <el-select v-model="form.type" placeholder="请选择分类">
                <el-option label="MySql" value="shanghai"></el-option>
                <el-option label="Java" value="beijing"></el-option>
                <el-option label="Spring" value="beijing"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="课件描述:">
          <el-input v-model="form.describe" placeholder="请输入课程描述" type="textarea"
                    :rows="3"></el-input>
        </el-form-item>
      </el-form>
    </div>
  </el-card>
    <el-card class="box-card" shadow="never" style="margin-top: 3px" v-show="isShow" v-for="(item,index) in dataList "  :key="index">
      <el-row >
        <el-col :span="3"><div class="grid-content bg-purple" style="text-align: center">
          <div style="border:1px solid #ebeef5;"><span>第{{item.hour}}课</span></div>
        </div></el-col>
        <el-col :span="14"><div class="grid-content bg-purple"style="text-align: center">
          <div style="border: 1px solid red;width: 178px;margin-left: 152px;"><span>{{item.name}}</span></div></div></el-col>
        <el-col :span="7"><div class="grid-content bg-purple"><div style="text-align: right"><i class="el-icon-close"></i></div></div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple" style="text-align: center">
          课程描述：
        </div></el-col>
        <el-col :span="20"><div class="grid-content bg-purple">{{item.describe}}</div></el-col>
        <el-col :span="13"><div class="grid-content bg-purple" style="text-align: center">
        </div></el-col>
        <el-col :span="10"><div class="grid-content bg-purple" style="color:rgb(59, 99, 190); margin-top: 10px;"><span>分类:{{item.type}}</span><span style="margin-left: 20px">作者:{{item.author}}</span></div></el-col>
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
          fileList2: [],
          state4: '',
          form: {
            name: '',
            hour: '',
            describe: '',
            type: '技术类',
            fileList: []
          },
          dataList: []
        }
      },
      mounted: function () {
        if (this.dataList.length) {
          this.isShow = true
        }
        this.restaurants = this.loadAll()
      },
      methods: {
        onSubmit () {

        },
        onAddCourse () {
          this.dataList.push(this.form)
          this.isShow = true
        },
        handleRemove (file, fileList) {
          console.log(file, fileList)
        },
        handlePreview (file) {
          console.log(file)
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
    width: 444px;
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
