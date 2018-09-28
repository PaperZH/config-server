<template>
  <el-dialog  :title="title" :visible.sync="visible" @close="$emit('update:show', false)" :show="show" >
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
                :key="item.userId"
                :label="item.nickname"
                :value="item.userId">
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
        <el-button type="primary" @click="remoteMethod">查询</el-button>
              <el-input style="width: 45%;margin-left: 5px"
                        v-model="queryParams.planTitle"
                        placeholder="请输入计划名称关键词"
              >
              </el-input>
              <el-table
                :data="plans"
                stripe
                v-loading="loading"
                tooltip-effect="dark"
                style="width: 100%;margin-top: 10px">
                <el-table-column
                  width="55">
                  <template slot-scope="scope">
                    <el-radio v-model="params.planId" :label="scope.row.id" >&nbsp</el-radio>
                  </template>
                </el-table-column>

                <el-table-column
                  label="计划名称"
                  prop="planTitle"
                  width="200">
                </el-table-column>
                <el-table-column
                  prop="builderName"
                  label="创建者"
                  width="150"
                >
                </el-table-column>
                <el-table-column
                  prop="planContent"
                  label="培训内容"
                  >
                </el-table-column>

              </el-table>
        <div class="block" style="text-align: right">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryParams.currentPage"
            :page-sizes="[6, 9, 12, 15]"
            :page-size="queryParams.pageSize"
            layout="total, prev, pager, next, jumper"
            :total="total">
          </el-pagination>
        </div>
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
        plans: [],
        total: 0,
        rules: {
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
        },
        queryParams: {
          pageSize: 5,
          currentPage: 1,
          planTitle: ''
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
        if (this.params.planId == null) {
          this.$message.error('请选择一个计划')
          return
        }
        this.$refs['message'].validate((valid) => {
          if (valid) {
            this.params.startDate = this.message.date[0]
            this.params.endDate = this.message.date[1]
            this.params.studentIds = this.message.studentIds
            this.$store.dispatch('Post', {'url': '/api-home/plan/addPublishedPlan', 'data': this.params}).then(res => {
              if (res.data.success) {
                this.$refs['message'].resetFields()
                this.message.date = []
                this.params.planId = null
                this.$emit('getTeacherPlan')
                this.$message.success('发布成功')
                this.visible = false
              } else {
                this.$message.error('发布失败')
              }
            })
          } else {
            return false
          }
        })
      },
      handleSizeChange (val) {
      },
      handleCurrentChange (val) {
        this.queryParams.currentPage = val
        this.remoteMethod()
      },
      remoteMethod () {
        this.loading = true
        this.$store.dispatch('Get', {'url': '/api-home/plan/getTeacherPlan', 'data': this.queryParams}).then(res => {
          this.loading = false
          if (res.data.success) {
            this.plans = res.data.re.rows
            this.total = res.data.re.total
          } else {
            this.plans = null
            this.total = 0
          }
        }).catch(error => {
          console.log(error)
        })
      },
      onCan () {
        this.visible = false
      }
    },
    mounted () {
      this.remoteMethod()
    }
  }
</script>

<style scoped>

</style>
