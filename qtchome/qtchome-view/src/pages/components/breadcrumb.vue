<template>
  <el-row :gutter="20" style="margin-left: 0px; margin-right: 0px;">
    <el-col :span="18" :offset="2"><div class="grid-content bg-purple">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ name: 'home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>课程</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    </el-col>
  </el-row>

</template>

<script>
    export default {
      name: 'breadcrumb'
    }
</script>

<style scoped>

</style>
