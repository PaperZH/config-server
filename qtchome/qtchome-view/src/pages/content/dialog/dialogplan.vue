<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show" style="width: 60% ;margin-left:350px ">
    <!--<el-dialog-->
      <!--width="30%"-->
      <!--title="分配课程"-->
      <!--:visible.sync="innerVisible"-->
      <!--append-to-body>-->
      <!--<el-form>-->
        <!--<el-row  v-for="(o, index) in coursess" :key="o.$index">-->
        <!--<el-col >-->
          <!--<el-form-item :label=o.studentName >-->
            <!--<el-select-->
              <!--v-model="o.course"-->
              <!--multiple-->
              <!--filterable-->
              <!--remote-->
              <!--reserve-keyword-->
              <!--placeholder="请输入课程关键词"-->
              <!--:remote-method="remoteMethod"-->
              <!--:loading="loading">-->
              <!--<el-option-->
                <!--v-for="item in options4"-->
                <!--:key="item.courseId"-->
                <!--:label="item.courseName"-->
                <!--:value="item.courseId">-->
              <!--</el-option>-->
            <!--</el-select>-->
          <!--</el-form-item>-->
        <!--</el-col>-->
          <!--</el-row>-->
        <!--<el-button type="primary" @click="print()">保存</el-button>-->
      <!--</el-form>-->
    <!--</el-dialog>-->
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
        <dialog-plan  show.sync="true" v-bind:message="message" title="发布计划" students="students"></dialog-plan>
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
  import dialogplan from '@/pages/content/dialog/addPublishedplan'  // 添加计划弹框
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
        state4: [
          {'courseId': 1, 'courseName': 'Java'},
          {'courseId': 2, 'courseName': 'Spring'},
          {'courseId': 3, 'courseName': 'CSS'}],
        coursess: [
          {'planId': '1', 'studentName': '张三'},
          {'planId': '2', 'studentName': '李四'},
          {'planId': '3', 'studentName': '王五'}
        ],
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
          this.visible = false
        })
      },
      print () {
        console.log('asdsad')
        this.$store.dispatch('Post', {'url': '/api-home/plan/addPublishedCourse', 'data': {'data': this.coursess}}).then(res => {
          console.log(res)
        })
        console.log(this.coursess)
      },
      remoteMethod (query) {
        if (query !== '') {
          this.loading = true
          setTimeout(() => {
            this.loading = false
            this.list = this.state4
            this.options4 = this.list
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
