<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:courseopen', false)" >
    <el-dialog
      width="30%"
      title="添加课程"
      :visible.sync="innerVisible"
      append-to-body>
      <el-select style="width: 70%"
        v-model="courseIds"
        multiple
        filterable
        remote
        reserve-keyword
        placeholder="请输入关键词"
        :remote-method="remoteMethod"
        :loading="loading">
        <el-option
          v-for="item in courses"
          :key="item.courseId"
          :label="item.courseName"
          :value="item.courseId">
        </el-option>
      </el-select>
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
      planId: {
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
        courseIds: []
      }
    },
    watch: {
      courseopen: function () {
        this.visible = this.courseopen
      }
    },
    methods: {
      addPlanCourse () {
        console.log(this.courseData)
        console.log(this.planId)
        this.innerVisible = true
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
            console.log(res)
          }).catch(_ => {
            this.$message({
              type: 'info',
              message: '删除失败'
            })
          })
        })
      },
      addCourse () {
        console.log(this.courseIds)
        if (this.courseIds.length === 0) {
          this.$message({
            message: '请添加至少一个课程',
            type: 'warning'
          })
        } else {
          let data = {'planId': this.planId, 'courseIds': this.courseIds}
          console.log(data)
          this.$store.dispatch('Post', {'url': '/api-home/plan/addPublishedCourse', 'data': data}).then(res => {
            console.log(res)
            let course = res.data.data
            for (let i = 0; i < course.length; i++) {
              this.courseData.unshift(course[i])
            }
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
      remoteMethod (query) {
        if (query !== '') {
          this.loading = true
          setTimeout(() => {
            this.$store.dispatch('Get', {'url': '/api-home/course/getCourseListByName', 'data': {'courseName': query}}).then(res => {
              this.courses = res.data.data
              this.loading = false
            })
          }, 200)
        } else {
          this.courses = []
        }
      }
    }
  }
</script>

<style scoped>

</style>
