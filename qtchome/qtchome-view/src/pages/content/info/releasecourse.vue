<template>
  <div style="border: 1px solid #dcdfe6;">

    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-top: 20px;margin-left: 50px">
      <el-form-item label="课程名">
        <el-input  v-model="formInline.name" placeholder="输入课程名称" size="small"></el-input>
      </el-form-item>
      <el-form-item label="日期">
        <el-date-picker  size="small"
                         v-model="formInline.date"
                         type="daterange"
                         range-separator="至"
                         start-placeholder="开始日期"
                         end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onsubmit" size="small">查询</el-button>
        <el-button  type="danger" @click="handledelete"size="small" >删除</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="24" style=" margin-left: 82px;margin-right: 96px; margin-top: 0px;">
      <el-checkbox-group v-model="checkList">
      <el-col :span="8" v-for="(o, index) in course" :key="o.courseId" style="margin-top: 20px;">
        <el-card :body-style="{ padding: '0px' } " >
          <div style="position: absolute; color: #172dff;">
            <el-checkbox :label="o.courseId">&nbsp</el-checkbox>
          </div>
          <img v-bind:src="o.courseCover"  class="image">
          <div style="padding: 7px;">
            <div  class="time" style="position: relative">
              <span style="display: block;float: left">{{o.courseName}}</span>
              <span style="float: right"><a v-on:click="handleRelease(o)" href="#">编辑</a></span>
            </div>
            <div class="bottom clearfix;"  style=" margin-top:  26px;">
              <span class="time">{{o.type_name}}</span>
              <span class="time" style="margin-left: 1%">
                 <i class="fa fa-thumbs-o-up" >0</i>
              </span>
              <div>
              <time class="time">2018-07-25 13:36</time>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
      </el-checkbox-group>
    </el-row>
    <div class="block" style="text-align: right">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
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
        currentPage4: 1,
        checked: false,
        formInline: {
          name: '',
          date: ''
        },
        queryParams: {
          userId: this.$store.getters.userId,
          courseName: '',
          startDate: '',
          endDate: '',
          currentPage: 1
        },
        checkList: [],
        course: [{
          courseId: 1,
          courseName: 'Spring+Java',
          courseType: 'shanghai',
          courseCover: 'static/image/5.jpg',
          courseDescription: '这是个测试说明'
        }, {
          courseId: 2,
          courseName: 'Spring+Java',
          courseType: 'shanghai',
          courseCover: 'static/image/5.jpg',
          courseDescription: '这是个测试说明'
        }, {
          courseId: 3,
          courseName: 'Spring+Java',
          courseType: 'shanghai',
          courseCover: 'static/image/5.jpg',
          courseDescription: '这是个测试说明'
        }, {
          courseId: 4,
          courseName: 'Spring+Java',
          courseType: 'shanghai',
          courseCover: 'static/image/5.jpg',
          courseDescription: '这是个测试说明'
        }, {
          courseId: 5,
          courseName: 'Spring+Java',
          courseType: 'shanghai',
          courseCover: 'static/image/5.jpg',
          courseDescription: '这是个测试说明'
        }, {
          courseId: 6,
          courseName: 'Spring+Java',
          courseType: 'shanghai',
          courseCover: 'static/image/5.jpg',
          courseDescription: '这是个测试说明'
        }, {
          courseId: 7,
          courseName: 'Spring+Java',
          courseType: 'shanghai',
          courseCover: 'static/image/5.jpg',
          courseDescription: '这是个测试说明'
        }, {
          courseId: 8,
          courseName: 'Spring+Java',
          courseType: 'shanghai',
          courseCover: 'static/image/5.jpg',
          courseDescription: '这是个测试说明'
        }, {
          courseId: 9,
          courseName: 'Spring+Java',
          courseType: 'shanghai',
          courseCover: 'static/image/5.jpg',
          courseDescription: '这是个测试说明'
        }
        ]
      }
    },
    methods: {
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
      },
      getHtml (val) {

      },
      handleRelease (o) {
        this.$router.push({name: 'release', params: o})
      },
      handledelete (val) {
        console.log(this.checkList)
      },
      onsubmit () {
        this.queryParams.courseName = this.formInline.name
        this.queryParams.startDate = this.formInline.date[0]
        this.queryParams.endDate = this.formInline.date[1]
        this.queryParams.currentPage = 1
        this.getPublishedCourse()
      },
      getPublishedCourse () {
        console.log(this.queryParams)
        this.$store.dispatch('Get', {'url': '/api-home/course/getPublishedCourse', 'data': this.queryParams}).then(res => {
          this.course = res.data.data
        })
      }
    },
    mounted () {
      this.getPublishedCourse()
    }
  }
</script>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top:  26px;
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
    clear: both;
  }
</style>
