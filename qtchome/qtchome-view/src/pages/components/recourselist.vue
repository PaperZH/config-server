<template>
  <div class=" fillcontain">
    <el-row :gutter="20" style="margin-left: 0px;margin-right: 0px; margin-top: 0px;">

      <div v-for="(item, index) in tableData" :key="index" style="padding: 11px; margin-top: 3px; width: 22%; float: left">
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
    </el-row>
  </div>


</template>

<script>
  export default {
    data () {
      return {
        tableData: [],
        queryParams: {
          courseName: null
        }
      }
    },
    methods: {
      handleDetails (val) {
        this.$router.push({name: 'details', params: val})
      }
    },
    created () {
      this.$store.dispatch('Get', {'url': '/api-home/course/getRecCourseList'}).then(res => {
        this.tableData = res.data.list
      })
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
