<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show" style="width: 60% ;margin-left:200px ">
    <el-form ref="message" :model="message" label-width="80px">
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
          <el-form-item label="学员" :span="11">
            <el-select
              v-model="studentIds"
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
          <el-form-item label="培训日期">
            <el-date-picker  style="width: 50%;"
              v-model="message.date"
              type="datetimerange"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              >
            </el-date-picker>
          </el-form-item>
            <el-form-item label="计划名称">
              <el-select  style="width: 50%"
                v-model="message.planTitle"
                filterable
                remote
                reserve-keyword
                placeholder="请输入关键词"
                :remote-method="remoteMethod"
                :loading="loading">
                <el-option
                  v-for="item in plans"
                  :key="item.planId"
                  :label="item.planTitle"
                  :value="item.planId">
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
  import dialogplan from '@/pages/content/dialog/dialogplan'  // 添加计划弹框
  export default {
    components: {
      'dialog-plan': dialogplan
    },
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
        type: String,
        default: ''
      }
    },
    data () {
      return {
        visible: this.show,
        loading: false,
        value6: '',
        value8: '',
        courses: [],
        studentIds: [],
        plans: []
      }
    },
    watch: {
      show: function () {
        this.visible = this.show
      }
    },
    methods: {
      onSubmit () {
        this.message.userId = this.$store.getters.userId
        console.log(this.message)
        this.$store.dispatch('Post', {'url': '/api-home/plan/addPublishedPlan', 'data': this.message}).then(res => {
          console.log(res)
        })
        this.$emit('transferUser', this.message)
        this.visible = false
      },
      remoteMethod (query) {
        if (query !== '') {
          this.loading = true
          setTimeout(() => {
            this.loading = false
            this.$store.dispatch('Get', {'url': '/api-home/plan/getPlanListByName', 'data': {'planName': query}}).then(res => {
              console.log(res)
              this.plans = res.data.data
            })
          }, 400)
        } else {
          this.courses = []
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
