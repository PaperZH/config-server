<template>

 <div class=" fillcontain">
   <page-head ref="head" @getActiveIndex="getActiveIndex" v-bind:activeIndex="activeIndex" v-bind:input="search"></page-head>
   <div style="width:100%;min-height:100%;position:relative;padding-bottom: 100px;">
     <router-view ></router-view>
   </div>

   <div style="
    width: 100%;position: fixed;
    bottom: 0px;">
     <page-footer></page-footer>
   </div>


 </div>


</template>
<script>
  import Head from '@/pages/components/head'  // 头部
  import Footer from '@/pages/components/footer' // 底部
  export default {
    components: {
      'page-head': Head,
      'page-footer': Footer
    },
    data () {
      return {
        activeIndex: '',
        search: ''
      }
    },
    mounted: function () {
      let tempPath = this.$route.path
      if (tempPath) {
        this.$router.push(tempPath)
      } else {
        this.$router.push('home')
      }
    },
    methods: {
      getActiveIndex (data) {
        this.$router.push(data)
      }
    }
  }
</script>
<style>

</style>
