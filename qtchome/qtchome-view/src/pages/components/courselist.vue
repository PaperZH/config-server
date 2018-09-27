<template>
  <div class=" fillcontain">
    <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px; margin-top: 0px;">

      <div v-for="(item, index) in tableData" :key="index" style="padding: 10px; margin-top: 3px; width: 22%; height: 290px; float: left">
        <el-card :body-style="{ padding: '0px',transition: 'all .2s linear' } " >
          <div v-on:click="handleDetails(item)">
            <div style="width: 100%; height: 200px;">
              <img v-bind:src="item.courseCover"  class="image">
            </div>
            <div style="padding: 9px;">
              <div>
                <span>{{item.courseName}}</span>
              </div>
              <div class="bottom clearfix">
                <el-row :gutter="24">
                  <el-col :span="8">
                    <span class="time">{{item.typeName}}</span>
                  </el-col>
                  <el-col :span="16">
                    <span class="time">
                      <el-col :span="8">
                        <i class="fa fa-thumbs-o-up" >{{item.praiseNum}}</i>
                      </el-col>
                      <el-col :span="8">
                        <i class="el-icon-star-on" >{{item.collectNum}}</i>
                      </el-col>
                      <el-col :span="8">
                        <i class="el-icon-edit" >{{item.readNum}}</i>
                      </el-col>
                    </span>
                  </el-col>
                </el-row>
                <el-row>
                  <span class="time">发布日期: {{item.publishTime}}</span>
                </el-row>
              </div>

            </div>
          </div>
        </el-card>
      </div>
      <!--<el-col :span="8">-->
        <!--<div class="block" style="display: inline; text-align: center; margin-top: 2%;">-->
          <!--<el-pagination-->
            <!--@size-change="handleSizeChange"-->
            <!--@current-change="handleCurrentChange"-->
            <!--:current-page="queryParams.currentPage"-->
            <!--:page-sizes="[12, 16, 20, 24]"-->
            <!--:page-size="queryParams.pageSize"-->
            <!--layout="sizes, total, prev, pager, next, jumper"-->
            <!--:total="total">-->
          <!--</el-pagination>-->
        <!--</div>-->
      <!--</el-col>-->

    </el-row>
    <div style="height: 50px; width:100%"
         v-loading="load.loading"
         :element-loading-text="load.loadTip"
         element-loading-spinner="el-icon-loading">
    </div>
  </div>

</template>

<script>
  export default {
    data () {
      return {
        total: 0,
        queryParams: {
          type: 'default',
          currentPage: 1,
          pageSize: 12,
          courseName: null,
          isInValidDate: true
        },
        load: {
          loading: false,
          loadFinished: false,
          loadTip: '正在加载...'
        },
        tableData: []
      }
    },
    computed: {
      searchCourseName () {
        if (sessionStorage.getItem('SearchCourseName')) {
          this.queryParams.courseName = sessionStorage.getItem('SearchCourseName')
        } else {
          this.queryParams.courseName = null
        }
      }
    },
    watch: {
      searchCourseName () {}
    },
    methods: {
      // handleSizeChange (val) {
      //   this.queryParams.pageSize = val
      //   this.queryParams.currentPage = 1
      //   this.getCourseList()
      // },
      // handleCurrentChange (val) {
      //   this.queryParams.currentPage = val
      //   this.getCourseList()
      // },

      handleDetails (val) {
        this.$router.push({name: 'details', params: val})
      },
      init () {
        this.total = 0
        this.tableData = []
        this.queryParams.type = sessionStorage.getItem('type')
        this.queryParams.currentPage = 1
      },
      getCourseList () {
        const url = '/api-home/frontPage/getCourseList'
        this.$store.dispatch('Post', {'url': url, 'data': this.queryParams}).then(res => {
          this.total = res.data.re.total
          this.tableData = res.data.re.rows
          // 如果已加载完所有的数据，则不再加载
          if (this.queryParams.currentPage * this.queryParams.pageSize >= this.total) {
            this.loadFinished = true
          }
        })
      },
      // 滚动加载数据
      scrollHandler () {
        // 如果正在加载数据,或者已加载完所有的数据，则不重复加载
        if (this.load.loading || this.load.loadFinished) {
          return
        }

        var bodyScrollTop = 0
        var documentScrollTop = 0
        if (document.body) {
          bodyScrollTop = document.body.scrollTop
        }
        if (document.documentElement) {
          documentScrollTop = document.documentElement.scrollTop
        }
        // 滚动条滚动过的距离
        var scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop
        // 页面的高度
        var clientHeight = document.body.clientHeight
        // 可以滚动的距离
        var scrollHeight = document.body.scrollHeight
        // 距离底部50px时，才加载数据。滚动条到底部的条件即为：scrollTop + clientHeight == scrollHeight
        if (scrollTop + clientHeight + 50 <= scrollHeight) {
          return
        }

        // 正在加载数据
        this.load.loading = true

        const url = '/api-home/frontPage/getCourseList'
        this.queryParams.currentPage = this.queryParams.currentPage + 1
        this.$store.dispatch('Post', {'url': url, 'data': this.queryParams}).then(res => {
          this.total = res.data.re.total
          this.tableData = this.tableData.concat(res.data.re.rows)
          if (this.queryParams.currentPage * this.queryParams.pageSize >= this.total) {
            // 已经加载完数据
            this.load.loadFinished = true
          }
          // 加载数据结束
          this.load.loading = false
        }).catch(() => {
          // 加载数据结束
          this.load.loading = false
        })
      }
    },
    mounted () {
      this.init()
      this.getCourseList()
      window.addEventListener('scroll', this.scrollHandler)
    },
    beforeDestroy () {
      window.removeEventListener('scroll', this.scrollHandler)
    }
  }
</script>

<style scoped>

  .is-always-shadow:hover{
    box-shadow: 5px 6px 2px 2px #CCCCCC;
    transform:translate3d(0,-5px,0);
  }
  .coursefont{
    font-size: 8px;
  }
  .coursefont >i{
    margin-left: 21%;
  }
  .coursefont>i+span {
    margin-left: 13%;
  }
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
    font-size: 13px;
    color: #999;
  }

  .image {
    width: 100%;
    height: 200px;
    display: block;
    backgroundRepeat:'no-repeat';
    backgroundSize: 'contain';
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
