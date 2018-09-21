<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:courseopen', false)" >
    <el-dialog
      width="50%"
      title="添加课程"
      :visible.sync="innerVisible"
      append-to-body>
      <el-input style="width: 70%"
        v-model="queryParams.courseName"
        placeholder="请输入关键词"
        >
      </el-input><el-button type="primary" @click="remoteMethod">查询</el-button>
      <el-checkbox-group v-model="courseIds" >
          <el-table
            :data="courses"
            v-loading="loading"
            stripe
            tooltip-effect="dark"
            style="width: 100%">
            <el-table-column
              width="55">
              <template slot-scope="scope">
                <el-checkbox :label="scope.row.id" >&nbsp</el-checkbox>
              </template>
            </el-table-column>
            <el-table-column
              label="课程名称"
              prop="courseName"
              width="200">
            </el-table-column>
            <el-table-column
              prop="teacherName"
              label="教师"
              width="120">
            </el-table-column>
            <el-table-column
              prop="courseDescription"
              label="课程描述"
             >
            </el-table-column>
          </el-table>
      </el-checkbox-group>
      <div class="block" style="text-align: right">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryParams.currentPage"
          :page-sizes="[6, 9, 12, 15]"
          :page-size="queryParams.pageSize"
          layout="total, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
      <el-button type="primary" @click="addCourse()">添加</el-button>
    </el-dialog>
    <el-button type="primary" @click="addPlanCourse">添加计划课程</el-button>
      <el-table :data="courseData">
        <el-table-column
          fixed
          label="序列"
          width="150" >
          <template slot-scope="scope" >
            {{scope.$index+1}}
          </template>
        </el-table-column>
        <el-table-column property="courseName" label="课程名" width="150"></el-table-column>
        <el-table-column property="courseDescription" label="课程描述" width="200"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              @click="handleDelete(scope.row,scope.$index)"
              size="mini"
              type="danger"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
</template>

<script>
  export default {
    props: {
      title: {
        type: String,
        default: ''
      },
      courseopen: {
        type: Boolean,
        default: true
      },
      courseData: {
        type: Array,
        default: []
      },
      id: {
        type: Number,
        default: 0
      }
    },
    data () {
      return {
        visible: this.courseopen,
        innerVisible: false,
        loading: false,
        courses: [],
        courseIds: [],
        checkList: [],
        total: 0,
        queryParams: {
          pageSize: 8,
          currentPage: 1,
          courseName: ''
        }
      }
    },
    watch: {
      courseopen: function () {
        this.visible = this.courseopen
      }
    },
    methods: {
      addPlanCourse () {
        this.innerVisible = true
        this.remoteMethod()
      },
      handleDelete (row, index) {
        this.$confirm('是否要删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('Get', {'url': '/api-home/plan/deletePlanCourse', 'data': {'id': row.id}}).then(res => {
            this.courseData.splice(index, 1)
            this.$message.success('删除成功')
          }).catch(_ => {
            this.$message({
              type: 'info',
              message: '删除失败'
            })
          })
        })
      },
      handleSizeChange (val) {
        this.queryParams.pageSize = val
      },
      handleCurrentChange (val) {
        this.queryParams.currentPage = val
        this.remoteMethod()
      },
      addCourse () {
        if (this.courseIds.length === 0) {
          this.$message({
            message: '请添加至少一个课程',
            type: 'warning'
          })
        } else {
          let data = {'planId': this.id, 'courseIds': this.courseIds}
          this.$store.dispatch('Post', {'url': '/api-home/plan/addPublishedCourse', 'data': data}).then(res => {
            this.$emit('handleCourse', this.id)
          }).catch(_ => {
            this.$message({
              showClose: true,
              message: '添加课程发生错误',
              type: 'error'
            })
          })
          this.innerVisible = false
        }
      },
      remoteMethod () {
        this.loading = true
        this.$store.dispatch('Get', {'url': '/api-home/plan/getCourseList', 'data': this.queryParams}).then(res => {
          this.courses = res.data.re.rows
          this.total = res.data.re.total
          this.loading = false
        })
      }
    }
  }
</script>

<style scoped>

</style>
