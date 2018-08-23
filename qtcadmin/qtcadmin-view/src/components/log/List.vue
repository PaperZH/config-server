<template>
  <el-row class="wrap">
    <el-col :span="24" class="warp-breadcrum">
      <el-breadcrumb>
        <el-breadcrumb-item>首页</el-breadcrumb-item>
        <el-breadcrumb-item>日志管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>

    <el-col :span="24" class="wrap-main" v-loading="loading" element-loading-text="拼命加载中">
      <!--工具条-->
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form :inline="true" :model="filters">
          <el-form-item>
            <el-input v-model="filters.username" placeholder="用户名" @keyup.enter.native="handleSearch"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-on:click="handleSearch">查询</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <el-table :data="rows" border highlight-current-row v-loading="loading" style="width: 100%;">
        <el-table-column type="selection" width="40">
        </el-table-column>
        <el-table-column label="用户" prop="username" align="center" width="100"></el-table-column>
        <el-table-column label="功能描述" prop="operation" align="center" width="120"></el-table-column>
        <el-table-column label="用时(ms)" prop="time" align="center" width="80"></el-table-column>
        <el-table-column label="方法" prop="method" align="center" width="400"></el-table-column>
        <el-table-column label="IP" prop="ip" align="center" width="160"></el-table-column>
        <el-table-column label="操作时间" prop="gmtCreate" align="center" width="160"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="remove(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>

    <!--工具条-->
    <el-col :span="24" class="toolbar" >
      <el-pagination layout="total,sizes, prev,pager, next,jumper" background
            @size-change="handleSizeChange"  
            @current-change="handleCurrentChange" 
            :page-size="limit"
            :total="total"
            :page-sizes="[10, 20, 30]">
      </el-pagination>
    </el-col>
  </el-row>
</template>

<script>
  import API from '../../api/api_log'

  export default {
    name: "List",
    data() {
      return {
        filters: {
          username: ""
        },
        loading: false,
        limit: 10,
        total: 0,
        page: 1,
        rows: []
      }
    },
    methods: {
      handleSizeChange(val) {
        this.limit = val;
        this.search();
      },
      handleCurrentChange(val) {
        this.page = val;
        this.search();
      },
      handleSearch() {
        this.total = 0;
        this.page = 1;
        this.search();
      },
      search: function (val) {
        let that = this
        let params = {
          limit: that.limit,
          page: that.page,
          username: that.filters.username
        }
        API.list(params).then(res => {
          if (res.code === 0) {
            that.rows = res.page.rows
            that.total = res.page.total
          }
        })

      },
      remove: function (id) {
        let that = this
        API.remove({id: id}).then(res => {
          if (res.code === 0) {
            that.$message.success(res.msg)
            that.search(that.page)
          }
        })
      },
    },

    mounted() {
      this.search(1);
    }
  }
</script>

<style scoped>
  .el-pagination {
    margin-top: 15px;
    margin-bottom: 15px;
    float: right
  }
</style>
