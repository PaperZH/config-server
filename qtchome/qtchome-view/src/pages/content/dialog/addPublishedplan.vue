<template>
  <el-dialog :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show">
    <el-form ref="message" :model="message" label-width="80px"  :rules="rules">
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
        <el-col :span="11">
          <el-form-item label="评分" :span="11" style="margin-left: -30px" prop="studentGetScore">
            <el-input
              placeholder="请给学员评分"
              v-model="message.studentGetScore">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="总分" >
            <el-input v-model="message.planScore" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col>
          <el-collapse v-model="activeName" accordion>
            <el-collapse-item title="培训内容" name="1">
              <el-input v-model="message.planContent" type="textarea"resize="none" disabled
                        :autosize="{ minRows: 8, maxRows: 12}"></el-input>
            </el-collapse-item>
            <el-collapse-item title="培训目的" name="2">
              <el-input v-model="message.planDestination"  type="textarea" resize="none"  disabled
                        :autosize="{ minRows: 8, maxRows: 12}"></el-input>
            </el-collapse-item>
            <el-collapse-item title="学员总结" name="4">
              <el-input v-model="message.studentSummary" type="textarea" resize="none" placeholder="暂无学习总结"
                        disabled  :autosize="{ minRows: 8, maxRows: 12}"></el-input>
            </el-collapse-item>
            <el-collapse-item title="计划评价" name="3" prop="teacherEvaluateContent">
              <el-input v-model="message.teacherEvaluateContent" type="textarea" resize="none" minlength="11"
                        placeholder="请输入评价"
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
        activeName: '3',
        plan: this.message.plan,
        value10: [],
        rules: {
          studentGetScore: [
            { required: true, message: '请输入计划学分', trigger: 'blur' },
            {pattern: /^[1-9](\d){0,2}$/, message: '请输入有效分数'}
          ]
        }
      }
    },
    watch: {
      show: function () {
        this.visible = this.show
      }
    },
    methods: {
      onSubmit () {
        this.$refs['message'].validate((valid) => {
          if (valid) {
            let data = {
              'studentGetScore': this.message.studentGetScore,
              'teacherEvaluateContent': this.message.teacherEvaluateContent,
              'id': this.message.id}
            console.log(data)
            this.$store.dispatch('Post', {'url': '/api-home/plan/addPublishedPlan', 'data': data}).then(res => {
              this.$emit('getTeacherPlan')
              this.visible = false
            })
          } else {
            console.log('失败')
          }
        })
      },
      onCan () {
        this.activeName = '3'
        this.visible = false
      }
    }
  }
</script>

<style scoped>

</style>
