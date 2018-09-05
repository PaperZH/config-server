<template>
  <div class=" fillcontain">
   <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;" >
     <el-col :span="20" :offset="2"><div class="grid-content bg-purple">
       <div class="block">
       <el-carousel trigger="click" :height="height">
         <el-carousel-item v-for="(img,index) in images" :key="index" >
           <img style="width: 100%;height: 430px;display: block; " @click="handleDetails(img.id)"  v-bind:src="img.bannerImg">
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
       <recourse-list></recourse-list>
     </div>
     </el-col>
   </el-row>
 </div>
</template>

<script>
    import ReCourseList from '@/pages/components/recourselist' // 推荐课程列表
    export default {
      components: {
        'recourse-list': ReCourseList
      },
      data () {
        return {
          height: '380px',
          images: [],
          bannerRes:[]
        }
      },
      created: function () {
        // this.$store.dispatch('Get', {'url': '/api-home/setTop/getBanner'}).then(res => {
        //   console.log(res)
        //   this.images = res.data.page.rows;
        // })
      },
      methods: {
        handleDetails (val) {
          this.$router.push({name: 'details', params: {courseId: val}
          })
        },
        handleCourse () {
          this.$router.push({name: 'course'})
        }
      }

    }
</script>

<style >
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
  }

</style>
