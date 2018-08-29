<template>




  <div style="border: 1px solid #dcdfe6;">

      <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-top: 20px;margin-left: 50px">
        <el-form-item label="课程名">
          <el-input  v-model="formInline.name" placeholder="输入计划名称" size="small"></el-input>
        </el-form-item>
        <el-form-item label="日期">
          <el-date-picker size="small"
            v-model="formInline.date"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handSearch" size="small">查询</el-button>
          <el-button  type="danger" @click="handleDel" size="small">删除</el-button>
        </el-form-item>
      </el-form>

    <el-row :gutter="24" style=" margin-left: 82px;margin-right: 96px; margin-top: 0px;">
      <el-checkbox-group v-model="checkList">
      <el-col :span="8" v-for="(o, index) in tableData" :key="o.courseId" style="margin-top: 20px;">
        <el-card :body-style="{ padding: '0px' } " >
          <div style="position: absolute; color: #172dff">
            <el-checkbox  :label="o.courseId">&nbsp</el-checkbox>
          </div>
          <img v-bind:src="o.courseCover"  class="image">
          <div style="padding: 7px;">
            <span class="time">{{o.courseName}}</span>
            <div class="bottom clearfix" style="margin-top: 6px;">
              <span class="time">{{o.type_name}}</span>
              <span class="time" style="    margin-left: 1%" >
                 <i class="fa fa-thumbs-o-up" >{{o.praiseNum}}</i>
              </span>
            </div>
            <time class="time">{{o.publishTime}}</time>
          </div>
        </el-card>
      </el-col>
      </el-checkbox-group>
    </el-row>
      <div class="block" style="text-align: right">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[100, 200, 300, 400]"
          :page-size="5"
          layout="total, prev, pager, next, jumper"
          :total="400">
        </el-pagination>
      </div>
  </div>
</template>

<script>
    export default {
      name: 'favorite',
      data () {
        return {
          currentPage: 1,
          checked: false,
          checkList: [],
          formInline: {
            name: '',
            date: ''
          },
          tableData: {},
          queryParams: {
            userId: this.$store.getters.userId,
            courseName: '',
            startDate: '',
            endDate: '',
            currentPage: 1,
            pageSize: 9
          }
        }
      },
      methods: {
        handleSizeChange (val) {
          console.log(`每页 ${val} 条`)
        },
        handleCurrentChange (val) {
          this.queryParams.currentPage = val
          this.getFavoriteCourse()
          console.log(`当前页: ${val}`)
        },
        getHtml (val) {

        },
        handleDel (val) {
          console.log(this.checkList)
          // let data = { courseId: [{'name': 'zhu', 'age': 10}, {'name': 'gou', 'age': 12}] }
          let data = {'userId': this.queryParams.userId, 'courseId': [1, 2, 3, 4]}
          this.$store.dispatch('Post', {'url': '/api-home/course/deleteFavoriteCourse', 'data': data}).then(res => {
            console.log(res.data)
          })
        },
        handSearch () {
          this.queryParams.courseName = this.formInline.name
          this.queryParams.startDate = this.formInline.date[0]
          this.queryParams.endDate = this.formInline.date[1]
          this.queryParams.currentPage = 1
          this.getFavoriteCourse()
        },
        getFavoriteCourse () {
          console.log(this.queryParams)
          this.$store.dispatch('Get', {'url': '/api-home/course/getFavoriteCourse', 'data': this.queryParams}).then(res => {
            this.tableData = res.data.data
          })
        }
      },
      mounted () {
        this.getFavoriteCourse()
      }
    }
</script>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;


  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
