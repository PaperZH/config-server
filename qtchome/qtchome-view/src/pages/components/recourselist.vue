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
  </div>


</template>

<script>
  export default {
    data () {
      return {
        tableData: []
      }
    },
    methods: {
      handleDetails (val) {
        this.$router.push({name: 'details', params: val})
      }
    },
    created () {
      this.$store.dispatch('Get', {'url': '/api-home/course/getRecCourse'}).then(res => {
        this.tableData = res.data.data
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
