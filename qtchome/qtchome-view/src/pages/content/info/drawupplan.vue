<template>
  <div>
  <el-card class="box-card" shadow="never">
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-top: 20px;margin-left: 50px">
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
        <el-button type="primary" @click="onAddPlan" size="small" >添加</el-button>
      </el-form-item>
    </el-form>
    <div style="border: 1px solid #dcdfe6;">
      <el-row :gutter="24" style="margin-left: 88px;margin-right: 123px; margin-top: 10px;">
        <el-col :span="4" v-for="(o, index) in indexList" :key="index" >
          <el-card :body-style="{ padding: '0px' }" v-bind:style="o.style?styleObject:''" >
            <img src="static/image/5.jpg" class="image" @click="addStyleClick(o)">
            <div style="padding: 14px;text-align: center">
              <span>{{o.name}}</span>
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
          prop="date"
          label="计划名称"
          width="250">
        </el-table-column>
        <el-table-column
          prop="name"
          label="学员"
          width="120">
        </el-table-column>
        <el-table-column
          prop="province"
          label="开始时间"
          width="220">
        </el-table-column>
        <el-table-column
          prop="city"
          label="结束时间"
          width="220">
        </el-table-column>
        <el-table-column
          prop="address"
          label="课程"
          width="280">
        </el-table-column>
        <el-table-column
          prop="zip"
          label="评分"
          width="100">
        </el-table-column>
        <el-table-column
          prop="zip"
          label="完成度"
          width="100">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              >删除</el-button>
          </template>
        </el-table-column>

      </el-table>
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
  </el-card>

  </div>
</template>

<script>
    export default {
      data () {
        return {
          show: false,
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
          formInline: {
            name: '',
            date: ''
          },
          currentDate: '2018-07-25 10:23',
          currentPage4: 1,
          tableData: [{
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-04',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }, {
            date: '2016-05-01',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }]
        }
      },
      methods: {
        onSubmit () {
          console.log('submit!')
        },
        onAddPlan () {
          this.show = true
        },
        handleClick (row) {
          console.log(row)
        },
        handleSizeChange (val) {
          console.log(`每页 ${val} 条`)
        },
        handleCurrentChange (val) {
          console.log(`当前页: ${val}`)
        },
        getMes (data) {
          console.log(data)
        },
        addStyleClick (o) {
          if (o.style) {
            o.style = false
          } else {
            o.style = true
          }
        }
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
