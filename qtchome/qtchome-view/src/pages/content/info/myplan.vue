<template>
  <el-card class="box-card" shadow="never">
  <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-top: 20px;">
    <el-form-item label="名称">
      <el-input v-model="formInline.name" placeholder="输入计划名称" size="small"></el-input>
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
      <el-button type="primary" @click="onSubmit" size="small">查询</el-button>
    </el-form-item>
  </el-form>
    <el-table
      border
      :data="dataPlan"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <el-table-column
        label="计划名称"
        width="180">
        <template slot-scope="scope">
          <a href="#">{{scope.row.name}}</a>
        </template>
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
        prop="state"
        label="完成状态">
      </el-table-column>
      <el-table-column
        prop="score"
        label="评分">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="onAddPlan">总结</el-button>
        </template>
      </el-table-column>
    </el-table>
    <dialog-edit-plan  :show.sync="show" v-bind:message="message" title="填写学习总结"></dialog-edit-plan>
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
  </el-card>
</template>

<script>
    export default {
      data () {
        return {
          show: false,
          formInline: {
            name: '',
            date: ''
          },
          message: {},
          value5: 3.7,
          currentPage4: 4,
          dataPlan: [{
            name: 'XXXXX培训计划',
            startDate: '2018-07-23',
            endDate: '2018-07-27',
            state: '未完成',
            score: 3.7,
            summary: '啊大苏打大苏打大苏打大撒',
            planTime: '1.部署开发环境；\n' +
            '2.尝试完成一个简单的web，要求实现用户与角色的管理；\n' +
            '3.熟悉idea、git、maven的使用；',
            planContent: '了解企业文化，学习公司开发规范\n' +
            '\n' +
            '2、熟悉面向对象编程思想，熟悉封装、继承、多态特性\n' +
            '\n' +
            '3、熟练掌握JavaSE的核心语法，'
          }, {
            name: 'XXXXX培训计划',
            startDate: '2018-07-23',
            endDate: '2018-07-27',
            state: '未完成',
            score: 3.7,
            summary: '啊大苏打大苏打大苏打大撒',
            planTime: '1.部署开发环境；\n' +
            '2.尝试完成一个简单的web，要求实现用户与角色的管理；\n' +
            '3.熟悉idea、git、maven的使用；',
            planContent: '了解企业文化，学习公司开发规范\n' +
            '\n' +
            '2、熟悉面向对象编程思想，熟悉封装、继承、多态特性\n' +
            '\n' +
            '3、熟练掌握JavaSE的核心语法，'
          }]
        }
      },
      methods: {
        onSubmit () {
          console.log('submit!')
        },
        handleSizeChange (val) {
          console.log(`每页 ${val} 条`)
        },
        handleCurrentChange (val) {
          console.log(`当前页: ${val}`)
        },
        getHtml (val) {

        },
        onAddPlan () {
          this.show = true
        },
        tableRowClassName ({row, rowIndex}) {
          if (rowIndex === 1) {
            return 'warning-row'
          } else if (rowIndex === 3) {
            return 'success-row'
          }
          return ''
        }
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
