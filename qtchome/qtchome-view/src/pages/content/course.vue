<template>
 <div style="margin-top: 1%;">
   <div>
     <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
       <el-col :span="18" :offset="3"><div class="grid-content bg-purple">
         <el-tabs v-model="activeName" @tab-click="handleClick">
           <el-tab-pane label="默认" name="first" ><course-list v-bind:isShow="isShow" type="default"></course-list></el-tab-pane>
           <el-tab-pane label="时间" name="third"><course-list v-bind:isShow="isShow" type="time" ></course-list></el-tab-pane>
           <el-tab-pane label="热度" name="fourth"><course-list v-bind:isShow="isShow" type="hot"></course-list></el-tab-pane>
         </el-tabs>
       </div>
       </el-col>
     </el-row>
   </div>
  </div>

</template>

<script>
    import CourseList from '@/pages/components/courselist' // 课程列表
    export default {
      components: {
        'course-list': CourseList
      },
      data () {
        return {
          activeName: 'first',
          isShow: true,
          type: 'default'
        }
      },
      methods: {
        handleClick (tab, event) {
          // console.log(tab, event)
        }
      }
    }
</script>

<style scoped>

</style>
