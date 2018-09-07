<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show">

    <el-form ref="message" :model="message" :rules="rules" label-width="80px">
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
        <el-col :span="10">
            <el-form-item label="计划名称" prop="planTitle">
               <el-input v-model="message.planTitle" > </el-input>
           </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="计划总分" prop="planScore">
            <el-input v-model="message.planScore"></el-input>
          </el-form-item>
        </el-col>
        <el-col >
          <el-form-item label="培训内容" prop="planContent">
            <el-input v-model="message.planContent" type="textarea"resize="none"
                       :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-form-item>
        </el-col>

        <el-col >
          <el-form-item label="培训目的" prop="planDestination">
            <el-input v-model="message.planDestination"  type="textarea" resize="none"
                       :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-form-item>
        </el-col>
        <el-col>
          <el-form-item style="text-align: right">
            <el-button type="primary" @click="onSubmit('message')">保存</el-button>
            <el-button type="primary" @click="onCan('message')">取消</el-button>
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
        loading: false,
        value6: '',
        value8: '',
        rules: {
          planTitle: [
            { required: true, message: '请输入计划名称', trigger: 'blur' }
          ],
          planScore: [
            { required: true, message: '请输入计划学分', trigger: 'blur' },
            {pattern: /^[1-9](\d){0,2}$/, message: '请输入有效分数'}
          ],
          planContent: [
            { required: true, message: '请输入计划内容', trigger: 'blur' }
          ],
          planDestination: [
            { required: true, message: '请输入计划目的', trigger: 'blur' }
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
      onSubmit (formName) {
        this.message.userId = this.$store.getters.userId
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$store.dispatch('Post', {'url': '/api-home/plan/addPlan', 'data': this.message}).then(res => {
              // this.$emit('EditClick')
              this.$refs.message.resetFields()
              this.visible = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      onCan () {
        this.visible = false
      }
    }
  }
</script>

<style scoped>

</style>
