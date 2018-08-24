<template>
  <div class=" fillcontain">
    <el-row :gutter="20" style="margin-left: 0px;margin-right: 0px; margin-top: 0px;">

      <div v-for="(item, index) in tableData" :key="index" style="padding: 11px; margin-top: 3px; width: 20%; float: left">
        <el-card :body-style="{ padding: '0px',transition: 'all .2s linear' } " >
          <div v-on:click="handleDetails(item)">
            <div  class="image" v-bind:style="{backgroundImage:'url(' + item.courseCover + ')', backgroundRepeat:'no-repeat', backgroundPosition:'center center', backgroundSize: 'contain'}"></div>
            <div style="padding: 9px;">
              <span >{{item.courseName}}</span>
              <div class="bottom clearfix">
                <span class="time">{{item.type_name}}</span>
                <span class="time" >
                 <i class="fa fa-thumbs-o-up" >{{item.praiseNum}}</i>
              </span>
                <time class="button">{{item.publishTime}}</time>
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </el-row>
    <div class="block" v-show="isShow" style="text-align: right; margin-top: 2%;">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="8"
        layout="total, prev, pager, next, jumper"
        :total=total>
      </el-pagination>
    </div>
  </div>


</template>

<script>
    export default {
      props: {
        isShow: {
          type: Boolean,
          default: false
        },
        type: {
          type: String,
          default: ' '
        }
      },
      data () {
        return {
          currentPage: 1,
          total: '',
          tableData: [{
            id: '1',
            date: '2017-06-02 14:45:00',
            tilt: 'Java 编程思想',
            number: 15,
            type: '技术类',
            img: 'static/image/1.jpg'
          }, {
            id: '2',
            date: '2018-07-04 14:45:00',
            tilt: 'spring入门精通',
            number: 6,
            type: '技术类',
            img: 'static/image/2.jpg'
          }, {
            id: '3',
            date: '2019-08-11 14:45:00',
            tilt: 'Mybatis入门精通',
            number: 9,
            type: '技术类',
            img: 'static/image/3.jpg'
          }, {
            id: '4',
            date: '2017-05-03 14:45:00',
            tilt: 'Kafka使用',
            number: 0,
            type: '技术类',
            img: 'static/image/4.jpg'
          }, {
            id: '1',
            date: '2017-06-02 14:45:00',
            tilt: 'Java 编程思想',
            number: 15,
            type: '技术类',
            img: 'static/image/1.jpg'
          }, {
            id: '2',
            date: '2018-07-04 14:45:00',
            tilt: 'spring入门精通',
            number: 6,
            type: '技术类',
            img: 'static/image/2.jpg'
          }, {
            id: '3',
            date: '2019-08-11 14:45:00',
            tilt: 'Mybatis入门精通',
            number: 9,
            type: '技术类',
            img: 'static/image/3.jpg'
          }, {
            id: '4',
            date: '2017-05-03 14:45:00',
            tilt: 'Kafka使用',
            number: 0,
            type: '技术类',
            img: 'static/image/4.jpg'
          }]
        }
      },
      methods: {
        handleSizeChange (val) {
          console.log(`每页 ${val} 条`)
        },
        handleCurrentChange (val) {
          console.log(`当前页: ${val}`)
          this.currentPage = val
          this.getCourseList()
        },
        handleDetails (val) {
          this.$router.push({name: 'details', params: val})
        },
        getCourseList () {
          const url = '/api-home/course/getList'
          const data = {'type': this.type, 'currentPage': this.currentPage, 'pageSize': 8}
          this.$store.dispatch('Get', {'url': url, 'data': data}).then(res => {
            console.log(res)
            this.tableData = res.data.re.rows
            this.total = res.data.re.total
          })
        }
      },
      mounted () {
        this.getCourseList()
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
    height: 200px;
    display: block;
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
