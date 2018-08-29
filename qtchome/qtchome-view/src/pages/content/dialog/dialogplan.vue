<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show">

    <el-form ref="message" :model="message" label-width="80px">
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
        <el-col :span="11">
          <el-form-item label="学员" :span="11">
            <el-select
              v-model="studentIds"
              multiple
               style="width: 90%;"
              placeholder="请选择学员">
              <el-option
                v-for="item in students"
                :key="item.studentId"
                :label="item.studentName"
                :value="item.studentId">
              </el-option>
            </el-select>
          </el-form-item>

        </el-col>
        <el-col :span="11">
          <el-form-item label="培训日期">
            <el-date-picker  style="width: 100%;"
              v-model="message.date"
              type="datetimerange"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              >
            </el-date-picker>
          </el-form-item>

        </el-col>
        <el-col :span="11">
          <el-form-item label="课程名称">
          <el-select  v-model="message.courseId"
                      filterable
                      remote
                      reserve-keyword
                      placeholder="请输入课程名"
                      :remote-method="remoteMethod"
                      :loading="loading"
            style="width:  90%;">
            <el-option
              v-for="item in courses"
              :key="item.courseId"
              :label="item.courseName"
              :value="item.courseId">
            </el-option>
          </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10">
            <el-form-item label="计划名称">
               <el-input v-model="message.titleName"></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-popover
            placement="top"
            width="400"
            trigger="click">
            <el-input placeholder="请输入历史计划名称"></el-input>
            <el-button slot="reference" size="small"style="margin-top: 8px;">历史计划11</el-button>
          </el-popover>
        </el-col>


        <el-col >
          <el-form-item label="培训内容">
            <el-input v-model="message.planContent" type="textarea"resize="none"
                       :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-form-item>
        </el-col>

        <el-col >
          <el-form-item label="培训目的">
            <el-input v-model="message.planDestination"  type="textarea" resize="none"
                       :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-form-item>
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
        value6: '',
        value8: '',
        courses: [],
        studentIds: []
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
        this.$store.dispatch('Post', {'url': '/api-home/plan/addPlan', 'data': this.message}).then(res => {
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
            this.$store.dispatch('Get', {'url': '/api-home/course/getCourseListByName', 'data': {'courseName': query}}).then(res => {
              this.courses = res.data.data
            })
          }, 200)
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
