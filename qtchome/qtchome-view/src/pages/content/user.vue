<template>
  <div class=" fillcontain" >
  <el-row :gutter="20" style="margin-top: 50px;margin-left: 0px; margin-right: 0px;">
    <el-col :span="3" :offset="2"><div class="grid-content bg-purple">
      <el-tabs :tab-position="tabPosition" style="max-height: 700px;" @tab-click="handleSelect">
        <el-tab-pane
          v-for="(item,index) in menus"
          :key="item.name"
          :label="item.name"
          :name="item.path"
        >
        </el-tab-pane>
      </el-tabs>
    </div>
    </el-col>
    <el-col :span="18"><div class="grid-content bg-purple" >
      <router-view ></router-view>
    </div></el-col>
  </el-row>
  </div>
</template>

<script>
export default {
  data () {
    return {
      tabPosition: 'left',
      menus: []
    }
  },
  mounted: function () {
    this.menus = JSON.parse(sessionStorage.getItem('access-menus'))
    if (this.menus != null) {
      this.$router.push(this.menus[0].path)
    }
  },
  methods: {
    handleSelect (data) {
      if (data.name) {
        this.$router.push(data.name)
      }
    }
  }
}
</script>

<style >
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>
