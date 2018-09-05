<template>
  <div>
    <el-card class="box-card" shadow="never">
      <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-top: 20px;margin-left: 50px">
        <el-form-item label="名称">
          <el-input v-model="formInline.name" placeholder="输入计划名称" size="small"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" size="small">查询</el-button>
          <el-button type="primary" @click="onAddPlan()" size="small" >发布新计划</el-button>
        </el-form-item>
      </el-form>
      <div style="border: 1px solid #dcdfe6;">
        <el-row :gutter="24" style="margin-left: 88px;margin-right: 123px; margin-top: 10px;">
          <el-col :span="4" v-for="(o, index) in indexList" :key="index" >
            <el-card :body-style="{ padding: '0px' }" v-bind:style="o.style?' ':styleObject" >
              <img :src=o.studentAvatar class="image" @click="addStyleClick(o)">
              <div style="padding: 14px;text-align: center">
                <span>{{o.studentName}}</span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
      <div style="margin-top: 10px">
        <el-table
          :data="tableData"
          border
          style="width: 100%">
          <el-table-column
            fixed
            prop="planTitle"
            label="计划名称"
            width="250">
          </el-table-column>
          <el-table-column
            prop="studentName"
            label="学员"
            width="120">
          </el-table-column>
          <el-table-column
            prop="startDate"
            label="开始时间"
            width="220">
          </el-table-column>
          <el-table-column
            prop="endDate"
            label="结束时间"
            width="220">
          </el-table-column>
          <el-table-column
            prop="studentGetScore"
            label="评分"
            width="100">
            <template slot-scope="scope">
              <p v-if="scope.row.studentGetScore!=null">
                {{scope.row.studentGetScore}}
              </p>
              <p v-else>暂未评分</p>
            </template>
          </el-table-column>
          <el-table-column
            label="课程"
            width="100"
           >
          <template slot-scope="scope" >
            <el-button
              type="primary"
              round
              @click="handleCourse(scope.row.planId)"
              size="small"
            >查看课程</el-button>
          </template>
          </el-table-column>
          <el-table-column label="操作" width="200px">
            <template slot-scope="scope">
              <el-button
                @click="handleEdit(scope.row.planId)"
                size="mini"
                type="info"
              >评价</el-button>
              <el-button
                @click="handleClick(scope.row,scope.$index)"
                size="mini"
                type="danger"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <plan-course  :courseopen.sync="courseopen" v-bind:courseData="courseData" title="计划课程信息" v-bind:planId="planId"></plan-course>
        <dialog-plan  :show.sync="show" v-bind:message="message" title="发布计划" v-bind:students="this.indexList"></dialog-plan>
        <edit-plan  :show.sync="edit" v-bind:message="planDetails" title="计划评价" @getTeacherPlan="getTeacherPlan"></edit-plan>
        <div class="block" style="text-align: right">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="5"
            layout="total, prev, pager, next, jumper"
            :total=total>
          </el-pagination>
        </div>
      </div>
    </el-card>

  </div>
</template>

<script>
  import dialogplan from '@/pages/content/dialog/dialogplan'  // 发布计划弹框
  import planCourse from '@/pages/content/dialog/planCourse'  // 计划课程弹框
  import editPlan from '@/pages/content/dialog/addPublishedplan'  // 编辑计划课程弹框
  export default {
    components: {
      'dialog-plan': dialogplan,
      'plan-course': planCourse,
      'edit-plan': editPlan
    },
    data () {
      return {
        show: false,
        courseopen: false,
        edit: false,
        courseData: [ ],
        planId: 0,
        indexList: [{
          name: '张三',
          style: false
        }, {
          name: '李斯',
          style: false
        }],
        styleObject: {
          border: '1px solid #409EFF'
        },
        message: {},
        planDetails: {'plan': { }},
        formInline: {
          name: '',
          date: ''
        },
        total: 0,
        currentDate: '2018-07-25 10:23',
        currentPage4: 1,
        tableData: [],
        queryParams: {
          userId: '',
          planName: '',
          currentPage: 1,
          pageSize: 5
        }
      }
    },
    methods: {
      onSubmit () {
        console.log('submit!')
        this.queryParams.planName = this.formInline.name
        this.queryParams.currentPage = 1
        this.getTeacherPlan()
      },
      onAddPlan () {
        this.show = true
      },
      handleCourse (planId) {
        console.log('查看course')
        this.$store.dispatch('Get', {'url': '/api-home/plan/getCourseByPlanId', 'data': {'planId': planId}}).then(res => {
          console.log(res)
          this.courseData = res.data.data
          this.courseopen = true
          this.planId = planId
        })
      },
      handleEdit (val) {
        console.log(val)
        this.$store.dispatch('Get', {'url': '/api-home/plan/getPlanDetails', 'data': {'planId': val}}).then(res => {
          console.log(res)
          this.planDetails = res.data.data
          this.edit = true
        })
      },
      handleClick (row, index) {
        console.log('delete')
        this.$confirm('是否要删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('Get', {'url': '/api-home/plan/deletePublishedPlan', 'data': {'planId': row.planId}}).then(res => {
            this.tableData.splice(index, 1)
            this.$message.success('删除成功')
            console.log(res)
          }).catch(_ => {
            this.$message({
              type: 'info',
              message: '删除失败'
            })
          })
        })
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        this.queryParams.currentPage = val
        this.getTeacherPlan()
        console.log(`当前页: ${val}`)
      },
      getMes (data) {
        console.log(data)
      },
      addStyleClick (o) {
        console.log(o)
        if (o.style) {
          o.style = false
        } else {
          o.style = true
        }
      },
      getTeacherPlan () {
        this.$store.dispatch('Get', {'url': '/api-home/plan/getPublishedPlan', 'data': this.queryParams}).then(res => {
          this.tableData = res.data.data
          this.indexList = res.data.students
          this.total = res.data.total
        })
      }
    },
    mounted () {
      this.queryParams.userId = this.$store.getters.userId
      this.getTeacherPlan()
    },
    name: 'drawupplan'
  }
</script>

<style >
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
