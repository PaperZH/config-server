<template>
  <el-card class="box-card" shadow="never">
  <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-top: 20px;">
    <el-form-item label="名称">
      <el-input v-model="formInline.name" placeholder="输入计划名称" size="small"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit" size="small">查询</el-button>
    </el-form-item>
  </el-form>
    <el-table
      border
      v-loading="loading"
      :data="dataPlan"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <el-table-column
        label="计划名称"
        width="180"
      prop="planTitle">
      </el-table-column>
      <el-table-column
        prop="startDate"
        label="开始时间">
      </el-table-column>
      <el-table-column
        prop="endDate"
        label="结束时间">
      </el-table-column>
      <el-table-column
        prop="studentGetScore"
        :formatter="formatterFinished"
        label="评分">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="onAddPlan(scope.row.id)">总结</el-button>
        </template>
      </el-table-column>
    </el-table>
    <dialog-edit-plan  :show.sync="show" v-bind:message="message" title="填写学习总结"></dialog-edit-plan>
    <div class="block" style="text-align: right">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="5"
        layout="total, prev, pager, next, jumper"
        :total=total>
      </el-pagination>
    </div>
  </el-card>
</template>

<script>
    import dialogplan from '@/pages/content/dialog/dialogeditplan'  // 添加计划弹框
    export default {
      components: {
        'dialog-edit-plan': dialogplan
      },
      data () {
        return {
          show: false,
          formInline: {
            name: '',
            date: ''
          },
          total: 0,
          message: {'plan': {}},
          loading: true,
          currentPage4: 4,
          dataPlan: [],
          queryParams: {
            studentId: this.$store.getters.userId,
            planTitle: null,
            currentPage: 1,
            pageSize: 5
          }
        }
      },
      methods: {
        onSubmit () {
          this.loading = true
          this.queryParams.planTitle = this.formInline.name
          this.queryParams.currentPage = 1
          this.getStudentPlan()
        },
        handleCurrentChange (val) {
          this.queryParams.currentPage = val
          this.getStudentPlan()
        },
        onAddPlan (val) {
          this.$store.dispatch('Get', {'url': '/api-home/plan/getPlanDetails', 'data': {'planId': val}}).then(res => {
            this.message = res.data.re
            this.show = true
          })
        },
        tableRowClassName ({row, rowIndex}) {
          if (rowIndex === 1) {
            return 'warning-row'
          } else if (rowIndex === 3) {
            return 'success-row'
          }
          return ''
        },
        getStudentPlan () {
          this.$store.dispatch('Get', {'url': '/api-home/plan/getStudentPlan', 'data': this.queryParams}).then(res => {
            this.loading = false
            this.dataPlan = res.data.re.rows
            this.total = res.data.re.total
          })
        },
        formatterFinished (row, column, cellValue, index) {
          if (cellValue === undefined) {
            return '暂未评分'
          } else {
            return cellValue
          }
        }
      },
      mounted () {
        this.getStudentPlan()
      },
      name: 'myplan'
    }
</script>

<style >
  .el-card__body {
    padding: 10px;
  }
  .el-rate {
    line-height: 1;
    width: 175px;
    float: right;
  }

  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
