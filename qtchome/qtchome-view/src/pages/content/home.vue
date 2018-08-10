<template>
  <div class=" fillcontain">
   <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;" >
     <el-col :span="20" :offset="2"><div class="grid-content bg-purple">
       <div class="block">
       <el-carousel trigger="click" :height="height">
         <el-carousel-item v-for="(img,index) in images" :key="index" >
           <img style="width: 100%;height: 430px;display: block;" v-bind:src="img.src">
         </el-carousel-item>
       </el-carousel>
       </div>
     </div></el-col>
   </el-row>
   <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
     <el-col :span="20" :offset="2" ><div class="grid-content bg-purple">
       <div style="margin-top: 10px;border-bottom: 1px solid #52464621 ;border-left-width: 5px ; border-left-color: red;border-left-style: solid;">
        <span style="margin-left: 5px;font-size: 26px;
         font-weight: bold;">课程</span>
         <span style="margin-left: 80%;"><a v-on:click="handleCourse" href="#">更多>>></a></span>
       </div>
       <course-ist></course-ist>
     </div>
     </el-col>
   </el-row>
 </div>
</template>

<script>
    export default {
      data () {
        return {
          height:'380px',
          images: [{
            id: 1,
            src: 'static/image/1.jpg'
          }, {
            id: 2,
            src: 'static/image/2.jpg'
          }, {
            id: 3,
            src: 'static/image/3.jpg'
          }, {
            id: 4,
            src: 'static/image/4.jpg'
          }, {
            id: 5,
            src: 'static/image/5.jpg'
          }, {
            id: 6,
            src: 'static/image/1.jpg'}]
        }
      },
      created: function () {

      },
      methods: {
        handleDetails (val) {
          this.$router.push({name: 'details', params: {id: val}
          })
        },
        handleCourse () {
          this.$router.push({name: 'course'})
        }
      }

    }
</script>

<style >

</style>
