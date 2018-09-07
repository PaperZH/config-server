<template>
  <div style="margin-top: 1%">
    <tabs-breadcrumb></tabs-breadcrumb>
    <div>
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
        <el-col :span="20" :offset="2">
          <div class="grid-content bg-purple" style="max-height:600px;">
            <img style="max-width: 65%; display: block; float: left;" v-bind:src="course.courseCover"/>
            <div style="float: right;min-width: 30%;" >
              <div style=" border-bottom: 1px solid #52464621;">
                <div><h2>{{course.courseName}}</h2></div>
                <div>
                  <el-row :gutter="24">
                    <el-col :span="10">
                      <span>课程类型：{{course.courseType.typeName}}</span>
                    </el-col>
                    <el-col :span="4">
                      <i class="fa fa-thumbs-o-up">{{course.praiseNum}}</i>
                    </el-col>
                    <el-col :span="4">
                      <i class="el-icon-star-on" >{{course.collectNum}}</i>
                    </el-col>
                    <el-col :span="4">
                      <i class="el-icon-edit" >{{course.readNum}}</i>
                    </el-col>
                  </el-row>
                    <span >上架时间：{{course.publishTime}}</span>
                </div>
              </div>
              <div>
                <div><h4>课程上传老师</h4></div>
                <div style="height: 116px;">
                  <div style="width: 40%;float: left"><img style="max-width: 80px;height: 70px" v-bind:src="teacher.avatar"/></div>
                  <div style="width: 60%;float: left"><span>{{teacher.nickname}}</span><br/><br/><br/><span>{{teacher.email}}</span></div>
                </div>
                <div>
                  <div style=""><span>课时数：{{tableData.length}}</span><br/><br/><br/><span>有效期：{{course.invalidDate}}</span></div>
                  <div style="text-align: center; margin-top: 20%;">
                    <el-button type="primary" round @click="handlePraiseCourse(course.courseId)" >{{praise.praiseText}}</el-button>
                    <el-button type="success" round @click="handleCollectCourse(course.courseId)">{{collect.collectText}}</el-button>
                    <el-button type="info" round @click="evaluateDialogVisible = true">评价</el-button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
        <el-col :span="20" :offset="2"><div class="grid-content bg-purple" >
          <el-tabs type="border-card" style="height: 320px;">
            <el-tab-pane label="课程介绍">
              <div>
                {{course.courseDescription}}
              </div>
            </el-tab-pane>
            <el-tab-pane label="课程目录"  ><div>
              <el-table
                :data="tableData"
                style="width: 100%;"
                height="210">
                <el-table-column
                  label="序列"
                  width="150" >
                  <template slot-scope="scope" >
                    第 {{scope.$index+1}} 课
                  </template>
                </el-table-column>

                <el-table-column
                  prop="name"
                  label="课件名称"
                  width="220">
                </el-table-column>
                <el-table-column
                  prop="publishTime"
                  label="上传日期"
                  width="230">
                </el-table-column>
                <el-table-column
                  prop="description"
                  label="描述"
                  width="230">
                </el-table-column>
                <el-table-column label="操作">
                  <template slot-scope="scope">
                    <el-button
                      size="mini" @click="handleDownloadClick(scope.row)"
                      type="danger"
                    >下载</el-button>
                    <el-button
                      size="mini" @click="handleStudyClick(scope.row)"
                      type="danger"
                    >学习</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-tab-pane>
          <el-tab-pane label="课程评价">
            <div>
              <el-table
                :data="evaluates"
                style="width: 100%;"
                height="210">
                <el-table-column
                  label="序列"
                  width="150" >
                  <template slot-scope="scope" >
                    第 {{scope.$index+1}} 楼
                  </template>
                </el-table-column>
                <el-table-column
                  prop="evaluateScore"
                  label="评分"
                  width="150">
                </el-table-column>
                <el-table-column
                  prop="evaluateContent"
                  label="评价内容"
                  width="800">
                </el-table-column>
                <el-table-column
                  prop="publishDate"
                  label="评价日期"
                  width="150">
                </el-table-column>
              </el-table>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
      </el-col>
    </el-row>
    </div>
    <el-dialog title="评价课程" :visible.sync="evaluateDialogVisible" width="30%" >
      <div class="block">
        <el-row :gutter="24">
          <el-col :span="8">
            <span class="demonstration">请评分：</span>
          </el-col>
          <el-col :span="14">
            <el-rate v-model="evaluateCourse.evaluateScore" :max="10" :colors="['#99A9BF', '#F7BA2A', '#FF9900']"> </el-rate>
          </el-col>
        </el-row>
      </div>
      <el-input type="textarea" :rows="5" placeholder="请输入评价内容" v-model="evaluateCourse.evaluateContent"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="evaluateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addEvaluateCourse(course.courseId)">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="" :visible.sync="studyDialogVisible" >
      <iframe :src="sourceUrl" style="width:100%; height: 600px"></iframe>
    </el-dialog>
  </div>

</template>

<script>
    import Breadcrumb from '@/pages/components/breadcrumb' //  面包屑 导航
    export default {
      components: {
        'tabs-breadcrumb': Breadcrumb
      },
      data () {
        return {
          evaluateDialogVisible: false,
          studyDialogVisible: false,
          sourceUrl: null,
          evaluateCourse: {
            evaluateScore: 5,
            evaluateContent: null
          },
          praise: {
            isPraised: null,
            praiseText: '点赞'
          },
          collect: {
            isCollected: null,
            collectText: '收藏'
          },
          course: {
            courseId: null,
            courseName: null,
            courseCover: null,
            praiseNum: null,
            publishTime: null,
            courseType: {
              id: null,
              typeName: null
            }
          },
          teacher: { },
          tableData: [],
          evaluates: []
        }
      },
      mounted: function () {
        this.$nextTick(function () {
          let id = this.$router.currentRoute.params.courseId
          this.getCourseDetails(id)
        })
      },
      methods: {
        handleSizeChange (val) {
          console.log(`每页 ${val} 条`)
        },
        handleCurrentChange (val) {
          console.log(`当前页: ${val}`)
        },
        handleDownloadClick (row) {
          let data = {'sourceUrl': row.sourceUrl}
          this.$store.dispatch('Post', {'url': '/api-home/file/download', 'data': data}).then(res => {
            console.log(res)
          })
        },
        handleStudyClick (row) {
          let sourceUrl = 'http://udfstest.10101111.com/ucarudfs/resource/' + row.sourceUrl
          this.sourceUrl = sourceUrl
          this.studyDialogVisible = true
        },
        handlePraiseCourse (val) {
          // let userId = this.$store.getters.userId
          let userId = 100
          if (userId == null || userId === '') {
            this.$notify.warning({'title': '点赞失败', 'message': '请先登陆'})
          } else {
            if (this.praise.isPraised === true) {
              this.$store.dispatch('Post', {'url': '/api-home/course/deletePraiseCourse/' + userId + '/' + val}).then(res => {
                if (res.data.success === true) {
                  this.praise.isPraised = false
                  this.praise.praiseText = '点赞'
                  this.getCourseDetails(val)
                }
              })
            } else {
              this.$store.dispatch('Post', {'url': '/api-home/course/addPraiseCourse/' + userId + '/' + val}).then(res => {
                if (res.data.success === true) {
                  this.praise.isPraised = true
                  this.praise.praiseText = '已赞'
                  this.getCourseDetails(val)
                }
              })
            }
          }
        },
        handleCollectCourse (val) {
          // let userId = this.$store.getters.userId
          let userId = 100
          if (userId == null || userId === '') {
            this.$notify.warning({'title': '收藏失败', 'message': '请先登陆'})
          } else {
            if (this.collect.isCollected === true) {
              let data = {'userId': userId, 'courseIds': [val]}
              this.$store.dispatch('Post', {'url': '/api-home/course/deleteCollectCourseList', 'data': data}).then(res => {
                if (res.data.success === true) {
                  this.collect.isCollected = false
                  this.collect.collectText = '收藏'
                  this.getCourseDetails(val)
                }
              })
            } else {
              this.$store.dispatch('Post', {'url': '/api-home/course/addCollectCourse/' + userId + '/' + val}).then(res => {
                if (res.data.success === true) {
                  this.collect.isCollected = true
                  this.collect.collectText = '已藏'
                  this.getCourseDetails(val)
                }
              })
            }
          }
        },
        addEvaluateCourse (val) {
          // let userId = this.$store.getters.userId
          let userId = 100
          this.evaluateDialogVisible = false
          let data = {'userId': userId, 'courseId': val, 'evaluateScore': this.evaluateCourse.evaluateScore, 'evaluateContent': this.evaluateCourse.evaluateContent}
          if (userId == null || userId === '') {
            this.$notify.warning({'title': '评价失败', 'message': '请先登陆'})
          } else {
            this.$store.dispatch('Post', {'url': '/api-home/course/addEvaluateCourse', 'data': data}).then(res => {
              if (res.data.success === true) {
                this.$notify.success('评价成功')
              }
            })
            this.getCourseDetails(val)
          }
        },
        getCourseDetails (val) {
          // let userId = this.$store.getters.userId
          let userId = 100
          // if (userId == null || userId === '') {
          //   this.$notify.warning({'title': '查看失败', 'message': '请先登陆'})
          // } else {
          this.$store.dispatch('Get', {'url': '/api-home/course/getDetails/' + val}).then(res => {
            this.course = res.data.re.course
            if (res.data.re.coursewares != null) {
              this.tableData = res.data.re.coursewares
            } else {
              this.tableData = []
            }
            this.teacher = res.data.re.teacher
            if (res.data.re.evaluates != null) {
              this.evaluates = res.data.re.evaluates
            } else {
              this.evaluates = []
            }
          })
          this.$store.dispatch('Post', {'url': '/api-home/course/isPraisedCourse/' + userId + '/' + val}).then(res => {
            if (res.data.success === true) {
              if (res.data.re === true) {
                this.praise.isPraised = true
                this.praise.praiseText = '已赞'
              } else {
                this.praise.isPraised = false
                this.praise.praiseText = '点赞'
              }
            }
          })
          this.$store.dispatch('Post', {'url': '/api-home/course/isCollectedCourse/' + userId + '/' + val}).then(res => {
            if (res.data.success === true) {
              if (res.data.re === true) {
                this.collect.isCollected = true
                this.collect.collectText = '已藏'
              } else {
                this.collect.isCollected = false
                this.collect.collectText = '收藏'
              }
            }
          })
          // }
        }
      }
    }
</script>

<style >
  .el-dialog{
    height: 80%
  }

</style>
