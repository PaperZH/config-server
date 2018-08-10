<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show">

    <el-form ref="message" :model="message" label-width="80px">
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
        <el-col :span="11">
          <el-form-item label="学员" :span="11">
            <el-select
              v-model="value10"
              multiple
               style="width: 90%;"
              placeholder="请选择学员">
              <el-option
                v-for="item in options5"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

        </el-col>
        <el-col :span="11">
          <el-form-item label="培训日期">
            <el-date-picker  style="width: 100%;"
              v-model="value6"
              type="datetimerange"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              >
            </el-date-picker>
          </el-form-item>

        </el-col>
        <el-col :span="11">
          <el-form-item label="课程名称">
          <el-select  multiple v-model="value8"  filterable placeholder="请选择课程" style="width:  90%;">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10">
            <el-form-item label="计划名称">
               <el-input v-model="message.name"><label>历史计划</label></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-popover
            placement="top"
            width="400"
            trigger="click">
            <el-input placeholder="请输入历史计划名称"></el-input>
            <el-button slot="reference" size="small"style="margin-top: 8px;">历史计划</el-button>
          </el-popover>
        </el-col>


        <el-col >
          <el-form-item label="培训内容">
            <el-input v-model="message.city" type="textarea"resize="none"
                       :autosize="{ minRows: 8, maxRows: 12}"></el-input>
          </el-form-item>
        </el-col>

        <el-col >
          <el-form-item label="培训目的">
            <el-input v-model="message.address"  type="textarea" resize="none"
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
