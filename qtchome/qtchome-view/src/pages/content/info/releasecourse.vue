<template>
  <div style="border: 1px solid #dcdfe6;">

    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-top: 20px;margin-left: 50px">
      <el-form-item label="课程名">
        <el-input  v-model="formInline.name" placeholder="输入课程名称" size="small" clearable></el-input>
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
        <el-button type="primary" @click="handleSearch" size="small">查询</el-button>
        <el-button  type="danger" @click="handleDelete"size="small" >删除</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="24" style=" margin-left: 82px;margin-right: 82px; margin-top: 0px;">
      <el-checkbox-group v-model="checkList">
      <el-col :span="8" v-for="(item, index) in tableData" :key="item.courseId" style="margin-top: 20px;">
        <el-card :body-style="{ padding: '0px' } " >

          <div style="position: absolute; color: #172dff;">
            <el-checkbox :label="item.courseId">&nbsp</el-checkbox>
          </div>

          <div style="width:100%; height: 200px">
            <img v-bind:src="item.courseCover" class="image">
          </div>

          <div style="padding: 7px;">
            <div  class="time" style="position: relative">
              <span style="display: block;float: left">{{item.courseName}}</span>
              <span style="float: right"><a v-on:click="handleRelease(item)" href="#">编辑</a></span>
            </div>

            <div class="bottom clearfix" style="margin-top: 26px;">
              <el-row :gutter="24">
                <el-col :span="8">
                  <span class="time">{{item.typeName}}</span>
                </el-col>
                <el-col :span="16">
                  <span class="time">
                    <el-col :span="8">
                      <i class="fa fa-thumbs-o-up" >{{item.praiseNum}}</i>
                    </el-col>
                    <el-col :span="8">
                      <i class="el-icon-star-on" >{{item.collectNum}}</i>
                    </el-col>
                    <el-col :span="8">
                      <i class="el-icon-edit" >{{item.readNum}}</i>
                    </el-col>
                  </span>
                </el-col>
              </el-row>
              <el-row>
                <span class="time">发布日期: {{item.publishTime}}</span>
              </el-row>
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
        :current-page.sync="queryParams.currentPage"
        :page-sizes="[6, 9, 12, 15]"
        :page-size="queryParams.pageSize"
        layout="total, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'favorite',
    data () {
      return {
        // currentPage4: 1,
        total: 0,
        // checked: false,
        checkList: [],
        formInline: {
          name: '',
          date: ''
        },
        tableData: {},
        queryParams: {
          // userId: this.$store.getters.userId,
          userId: 100,
          courseName: null,
          startDate: null,
          endDate: null,
          currentPage: 1,
          pageSize: 6
        }
      }
    },
    methods: {
      handleSizeChange (val) {
        this.queryParams.pageSize = val
        this.getPublishedCourse()
      },
      handleCurrentChange (val) {
        this.queryParams.currentPage = val
        this.getPublishedCourse()
      },
      handleRelease (item) {
        this.$router.push({name: 'release', params: item})
      },
      handleDelete (val) {
        let data = {'userId': this.queryParams.userId, 'courseIds': this.checkList}
        this.$store.dispatch('Post', {'url': '/api-home/course/deletePublishedCourse', 'data': data}).then(res => {
          this.getPublishedCourse()
        })
      },
      handleSearch () {
        if (this.formInline.name.trim().length === 0) {
          this.queryParams.courseName = null
        } else {
          this.queryParams.courseName = this.formInline.name.trim()
        }
        if (this.formInline.date == null) {
          this.queryParams.startDate = null
          this.queryParams.endDate = null
        } else {
          this.queryParams.startDate = this.formInline.date[0]
          this.queryParams.endDate = this.formInline.date[1]
        }
        this.queryParams.currentPage = 1
        this.checkList = []
        this.getPublishedCourse()
      },
      getPublishedCourse () {
        this.$store.dispatch('Get', {'url': '/api-home/course/getPublishedCourse', 'data': this.queryParams}).then(res => {
          this.total = res.data.re.total
          this.tableData = res.data.re.rows
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
    height: 200px;
    display: block;
    backgroundRepeat:'no-repeat';
    backgroundSize: 'contain';
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
