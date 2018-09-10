<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show" style="width: 60% ;margin-left:350px ">
    <el-form ref="message" :model="message" label-width="80px"  :rules="rules">
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
          <el-form-item label="学员" :span="11" prop="studentIds">
            <el-select
              v-model="message.studentIds"
              multiple
               style="width: 50%;"
              placeholder="请选择学员">
              <el-option
                v-for="item in students"
                :key="item.studentId"
                :label="item.studentName"
                :value="item.studentId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="培训日期" prop="date">
            <el-date-picker  style="width: 50%;"
              v-model="message.date"
              type="datetimerange"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              >
            </el-date-picker>
          </el-form-item>
            <el-form-item label="计划名称" prop="planId">
              <el-select  style="width: 50%"
                v-model="message.planId"
                filterable
                remote
                reserve-keyword
                placeholder="请输入关键词"
                :remote-method="remoteMethod"
                :loading="loading">
                <el-option
                  v-for="item in plans"
                  :key="item.id"
                  :label="item.planTitle"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
        <el-col>
          <el-form-item style="text-align: left">
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
      },
      students: {
        type: Array,
        default: []
      }
    },
    data () {
      return {
        visible: this.show,
        loading: false,
        options4: [],
        list: [],
        courses: [],
        plans: [],
        rules: {
          planId: [
            {required: true, message: '请输入计划名称', trigger: 'blur'}
          ],
          studentIds: [
            {type: 'array', required: true, message: '请选择学员', trigger: 'blur'}
          ],
          date: [
            {type: 'array', required: true, message: '请选择日期', trigger: 'blur'}
          ]
        },
        params: {
          'teacherId': this.$store.getters.userId,
          'startDate': null,
          'endDate': null,
          'studentIds': null,
          'planId': null
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
        console.log(this.message.date)
        this.$refs['message'].validate((valid) => {
          if (valid) {
            if (this.message.date.length <= 1) {
              this.$message.error('请输入日期')
              return
            }
            this.params.startDate = this.message.date[0]
            this.params.endDate = this.message.date[1]
            this.params.studentIds = this.message.studentIds
            this.params.planId = this.message.planId
            this.$store.dispatch('Post', {'url': '/api-home/plan/addPublishedPlan', 'data': this.params}).then(res => {
              console.log(res)
              this.$refs['message'].resetFields()
              this.$emit('getTeacherPlan')
              this.visible = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      remoteMethod (query) {
        if (query !== '') {
          this.loading = true
          setTimeout(() => {
            this.$store.dispatch('Get', {'url': '/api-home/plan/getTeacherPlan', 'data': {'planTitle': query}}).then(res => {
              console.log(res)
              this.plans = res.data.re.rows
              this.loading = false
            })
          }, 400)
        } else {
          this.plans = []
        }
      },
      onCan () {
        this.visible = false
      }
    }
  }
</script>

<style scoped>

</style>
