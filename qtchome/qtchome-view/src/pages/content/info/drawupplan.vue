<template>
  <div>
  <el-card class="box-card" shadow="never">
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-top: 20px;margin-left: 50px">
      <el-form-item label="名称">
        <el-input v-model="formInline.name" placeholder="输入计划名称" size="small"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" size="small">查询</el-button>
        <el-button type="primary" @click="onAddPlan()" size="small" >制定新计划</el-button>
      </el-form-item>
    </el-form>
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
          prop="planContent"
          label="培训内容"
          width="400">
        </el-table-column>
        <el-table-column
          prop="planDestination"
          label="培训目的"
          width="400">
        </el-table-column>
        <el-table-column
          prop="planScore"
          label="总分"
          width="100">
        </el-table-column>
        <el-table-column
          label="课程"
          width="100"
        >
          <template slot-scope="scope" >
            <el-button
              type="primary"
              round
              @click="handleCourse(scope.row.id)"
              size="small"
            >分配课程</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" @click="handleEdit(scope.row)"></el-button>
            <el-button type="primary" icon="el-icon-delete"  @click="handleClick(scope.row,scope.$index)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <dialog-plan  :show.sync="show" v-bind:message="message" :title="title"  @EditClick="getTeacherPlan"></dialog-plan>
      <plan-course  :courseopen.sync="courseopen" v-bind:courseData="courseData" title="计划课程信息" v-bind:id="id" @handleCourse="handleCourse"></plan-course>
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
    import dialogplan from '@/pages/content/dialog/addPlan'  // 添加计划弹框
    import planCourse from '@/pages/content/dialog/planCourse'  // 计划课程弹框
    export default {
      components: {
        'dialog-plan': dialogplan,
        'plan-course': planCourse
      },
      data () {
        return {
          show: false,
          courseopen: false,
          courseData: [],
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
          title: '',
          message: {},
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
            planTitle: null,
            currentPage: 1,
            pageSize: 5
          }
        }
      },
      methods: {
        onSubmit () {
          console.log('submit!')
          this.queryParams.planTitle = this.formInline.name
          this.queryParams.currentPage = 1
          this.getTeacherPlan()
        },
        onAddPlan () {
          this.message = {}
          this.title = '制定新计划'
          this.show = true
        },
        handleClick (row, index) {
          console.log('delete')
          this.$confirm('是否要删除该记录?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$store.dispatch('Get', {'url': '/api-home/plan/deletePlan', 'data': {'planId': row.id}}).then(res => {
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
        handleEdit (val) {
          this.title = '编辑计划'
          this.show = true
          let data = val
          this.message = data
        },
        handleCourse (planId) {
          console.log('查看course')
          this.$store.dispatch('Get', {'url': '/api-home/plan/getCourseByPlanId', 'data': {'planId': planId}}).then(res => {
            console.log(res)
            this.courseData = res.data.re
            this.id = planId
            this.courseopen = true
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
        formatterFinished (row, column, cellValue, index) {
          if (cellValue === true) {
            return '已完成'
          } else {
            return '未完成'
          }
        },
        getTeacherPlan () {
          console.log(this.queryParams)
          this.$store.dispatch('Get', {'url': '/api-home/plan/getTeacherPlan', 'data': this.queryParams}).then(res => {
            console.log(res)
            this.tableData = res.data.re.rows
            this.total = res.data.re.total
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
