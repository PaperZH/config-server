<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show" >
    <el-form ref="message" :model="message" label-width="80px"  >
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
        <el-col :span="11">
          <el-form-item label="教师" :span="11">
            <el-input
              placeholder="教师姓名"
              v-model="message.teacherName"
              readonly>
            </el-input>
          </el-form-item>
        </el-col>
        <el-popover
          placement="right"
          width="400"
          trigger="click">
          <el-table :data="courseData">
            <el-table-column width="150" property="courseName" label="课程名称"></el-table-column>
            <el-table-column width="100" property="courseDescription" label="课程描述"></el-table-column>
          </el-table>
          <el-button slot="reference">查看课程信息</el-button>
        </el-popover>
        <el-col >
          <el-form-item label="培训内容" >
            <el-input v-model="message.planContent" type="textarea"resize="none" readonly
                      :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-form-item>
        </el-col>

        <el-col >
          <el-form-item label="培训目的">
            <el-input v-model="message.planDestination"  type="textarea" resize="none"  readonly
                      :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-form-item>
        </el-col>
        <el-col >
          <el-form-item label="总结">
            <el-input v-model="message.studentSummary"  type="textarea" resize="none"
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
      }
    },
    data () {
      return {
        visible: this.show,
        courseData: [],
        value6: '',
        value8: '',
        options: [{
          value: '选项1',
          label: 'java 虚拟机'
        }, {
          value: '选项2',
          label: 'Springvc'
        }],
        options5: [{
          value: 'HTML',
          label: '张三'
        }, {
          value: 'CSS',
          label: '李斯'
        }, {
          value: 'JavaScript',
          label: '隔壁老王'
        }],
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
        console.log(this)
        this.$emit('transferUser', this.message)
        this.visible = false
      },
      onCan () {
        this.visible = false
      }
    }
  }
</script>

<style scoped>

</style>
