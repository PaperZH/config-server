<template>
  <div id="app">
    <!--<router-view class="app-container"/>-->
    <router-view v-if="isRouterAlive"></router-view>
  </div>

</template>

<script>
 // import { mapActions } from 'vuex'
  export default {
    name:'app',
    provide(){
      return {
        reload:this.reload
      }
    },
    data(){
      return {
        isRouterAlive:true
      }
    },

    mounted () {
      /**
       * 根据携带的cookie，自动获取当前系统的所有按钮权限，会发起一个http请求
       */

    },
    methods: {
      reload(){
        this.isRouterAlive=false
        this.$nextTick(function () {
          this.isRouterAlive=true
        })
      }
    }
  }
</script>


<style lang="scss">
  html, body, #app {
    margin: 0;
    padding: 0;
    height: 100%;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  .app-iframe-container {
    padding: 20px;
    height: 100%;
    overflow: auto;
  }

  .app-iframe-container .row-title {
    display: flex;
    align-items: center;
    border-bottom: 1px solid #ddd;
    margin-bottom: 15px;
  }

  .app-iframe-container .row-title h2 {
    color: #409eff;
    font-size: 20px;
    line-height: 20px;
    font-weight: bolder;
    border-left: 2px solid #409eff;
    padding-left: 10px;
    margin-right: 20px;
  }

  .app-iframe-container .top-15 {
    margin-top: 15px;
  }

  .app-iframe-container .table-wrapper {
    border: 1px solid #ddd;
    padding: 20px;
    box-shadow: 0 2px 3px 0 #ddd;
  }

  .app-iframe-container .choose-input .el-input {
    width: 80%;
  }

  .app-iframe-container {
    .tips {
      margin-bottom: 10px;
      .green {
        color: #67c23a;
      }
      .blue {
        color: #409eff;
      }
      .red {
        color: #f56c6c;
      }
    }
  }
</style>
