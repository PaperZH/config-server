<template>
 <div style="margin-top: 1%;">
   <div>
     <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
       <el-col :span="18" :offset="3">
         <div class="grid-content bg-purple">
           <el-button type="text" @click="changeSearchType('default')" style="font-size: 18px">默认</el-button>
           <el-button type="text" @click="changeSearchType('time')" style="font-size: 18px">时间</el-button>
           <el-button type="text" @click="changeSearchType('hot')" style="font-size: 18px">热度</el-button>
           <course-list></course-list>
         </div>
       </el-col>
     </el-row>
   </div>
  </div>
</template>

<script>
    import CourseList from '@/pages/components/courselist' // 课程列表
    export default {
      inject: ['reload'],
      components: {
        'course-list': CourseList
      },
      methods: {
        changeSearchType (type) {
          sessionStorage.setItem('type', type)
          this.reload()
        }
      },
      mounted () {
        sessionStorage.setItem('type', 'default')
      }
    }
</script>

<style scoped>

</style>
