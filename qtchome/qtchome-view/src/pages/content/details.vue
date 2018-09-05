<template>
  <div style="margin-top: 1%">
    <tabs-breadcrumb></tabs-breadcrumb>
    <div>
      <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
        <el-col :span="20" :offset="2"><div class="grid-content bg-purple" style="max-height:600px;">
          <img style="max-width: 65%; display: block; float: left;" v-bind:src="items.courseCover"/>
          <div style="float: right;min-width: 30%;" >
            <div style=" border-bottom: 1px solid #52464621;">
              <div><h2>{{items.courseName}}</h2></div>
              <div>
                <span>课程类型:{{items.typeName}}</span>
                <span >上架时间:{{items.publishTime}}</span>
                <i class="fa fa-thumbs-o-up">{{items.praiseNum}}</i>
              </div>
            </div>
            <div>
              <div><h4>课程上传老师</h4></div>
              <div style="height: 116px;">
                <div style="width: 40%;float: left"><img style="max-width: 80px;height: 70px" v-bind:src="teacher.avatar"/></div>
                <div style="width: 60%;float: left"><span>{{teacher.username}}</span><br/><br/><br/><span>{{teacher.email}}</span></div>
              </div>
              <div>
                <div style=""><span>课时数：{{tableData.length}}</span><br/><br/><br/><span>有效期：{{items.invalidDate}}</span></div>
                <div style="text-align: center; margin-top: 20%;">
                  <el-button type="danger" round @click="handlePraiseCourse(items.courseId)" >{{praise.praiseText}}</el-button>
                  <el-button type="danger" round @click="addCollectCourse(items.courseId)">收藏</el-button>
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
                {{items.courseDescription}}
              </div>
            </el-tab-pane>
            <el-tab-pane label="课程目录"  ><div>
              <el-table
                :data="tableData"
                style="width: 100%;"
                height="210">
                <el-table-column
                  fixed
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
                  fixed
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
                      size="mini" @click="handleClick(scope.row)"
                      type="danger"
                    >下载</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
                <!--<div class="block" style="text-align: center">-->
              <!--<el-pagination-->
                <!--@size-change="handleSizeChange"-->
                <!--@current-change="handleCurrentChange"-->
                <!--:current-page="currentPage4"-->
                <!--:page-sizes="[100, 200, 300, 400]"-->
                <!--:page-size="100"-->
                <!--layout="total, sizes, prev, pager, next, jumper"-->
                <!--:total="400">-->
              <!--</el-pagination>-->
            <!--</div>-->
          </el-tab-pane>
        </el-tabs>
      </div>
      </el-col>
    </el-row>
    </div>
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
          praise: {
            isPraised: null,
            praiseText: ''
          },
          items: {},
          currentPage4: 5,
          teacher: { },
          tableData: [{
            date: '2016-05-04 01:00:0',
            name: 'Java基础运算',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01 01:00:0',
            name: 'Java集合框架',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            date: '2016-05-03 01:00:0',
            name: 'Java多线程',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
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
        handleClick (row) {
          let data = {'sourceUrl': row.sourceUrl}
          this.$store.dispatch('Post', {'url': '/api-home/file/download', 'data': data}).then(res => {
            console.log(res)
          })
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
                }
              })
            } else {
              this.$store.dispatch('Post', {'url': '/api-home/course/addPraiseCourse/' + userId + '/' + val}).then(res => {
                if (res.data.success === true) {
                  this.praise.isPraised = true
                  this.praise.praiseText = '已赞'
                }
              })
            }
          }
        },
        addCollectCourse (val) {
          // let userId = this.$store.getters.userId
          let userId = 100
          if (userId == null || userId === '') {
            this.$notify.warning({'title': '收藏失败', 'message': '请先登陆'})
          } else {
            this.$store.dispatch('Post', {'url': '/api-home/course/addCollectCourse/' + userId + '/' + val}).then(res => {
              if (res.data.success === true) {
                this.$notify.success('收藏成功')
              }
            })
          }
        },
        getCourseDetails (val) {
          // let userId = this.$store.getters.userId
          let userId = 100
          // if (userId == null || userId === '') {
          //   this.$notify.warning({'title': '查看失败', 'message': '请先登陆'})
          // } else {
          this.$store.dispatch('Get', {'url': '/api-home/course/getDetails/' + val}).then(res => {
            console.log(res)
            this.items = res.data.re.course
            if (res.data.re.coursewares != null) {
              this.tableData = res.data.re.coursewares
            } else {
              this.tableData = []
            }
            this.teacher = this.teacher
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
          // }
        }
      }
    }
</script>

<style scoped>

</style>
