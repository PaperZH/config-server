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
  export default {
    data () {
      return {
        activeIndex: 'home',
        search: ''
      }
    },
    mounted: function () {
      this.$router.push('home')
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
