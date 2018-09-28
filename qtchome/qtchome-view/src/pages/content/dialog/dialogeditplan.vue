<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show" >
    <el-form ref="message" :model="message" label-width="80px"  >
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
        <el-col :span="11">
          <el-form-item label="导师" :span="11" style="margin-left: -30px" >
            <el-input
              placeholder="教师姓名"
              v-model="message.teacherName"
              readonly>
            </el-input>
          </el-form-item>
        </el-col>
        <el-popover
          placement="right-start"
          width="500"
          trigger="click">
          <el-table :data="message.courses">
            <el-table-column width="150" property="courseName" label="课程名称"></el-table-column>
            <el-table-column  show-overflow-tooltip property="courseDescription" label="课程描述"></el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="handleSearch(scope.row.courseId)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-button slot="reference">查看课程信息</el-button>
        </el-popover>
        <el-col>
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item title="培训内容" name="1">
              <el-input v-model="message.planContent" type="textarea"resize="none"  disabled
                        :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-collapse-item>
          <el-collapse-item title="培训目的" name="2">
            <el-input v-model="message.planDestination"  type="textarea" resize="none"  disabled
                      :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-collapse-item>
          <el-collapse-item title="计划评价" name="3">
            <el-input v-model="message.studentEvaluateContent"  type="textarea" resize="none" placeholder="输入学习内容"
                      :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-collapse-item>
          <el-collapse-item title="学习总结" name="4">
            <el-input v-model="message.studentSummary"  type="textarea" resize="none" placeholder="输入学习总结"
                      :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-collapse-item>
        </el-collapse>
        </el-col>
        <el-col>
          <el-form-item style="text-align: right">
            <el-button type="primary" @click="onSubmit">保存</el-button>
            <el-button type="primary" @click="onCan">取消</el-button>
          </el-form-item>
        </el-col>

      </el-row>
    </el-form>
  </el-dialog>
</template>

<script>
  export default {
    props: {
      title: {
        type: String,
        default: ''
      },
      show: {
        type: Boolean,
        default: false
      },
      message: {
        type: Object,
        default: {}
      }
    },
    data () {
      return {
        visible: this.show,
        activeName: '4',
        plan: this.message.plan,
        value10: []
      }
    },
    watch: {
      show: function () {
        this.visible = this.show
      }
    },
    methods: {
      onSubmit () {
        let studentSummary = this.message.studentSummary
        if (studentSummary === null || studentSummary === '') {
          this.$message.error('请输入总结')
          return false
        }
        let data = {
          'studentEvaluateContent': this.message.studentEvaluateContent,
          'studentSummary': studentSummary,
          'id': this.message.id
        }
        this.$store.dispatch('Post', {'url': '/api-home/plan/updatePublishedPlan', 'data': data}).then(res => {
          if (res.data.success) {
            this.$message.success('总结成功')
            this.visible = false
          } else {
            this.$message.error('总结失败')
          }
        })
      },
      handleSearch (courseId) {
        this.$router.push({name: 'details', params: {'courseId': courseId}})
      },
      onCan () {
        this.activeName = '4'
        this.visible = false
      }
    }
  }
</script>

<style scoped>

</style>
