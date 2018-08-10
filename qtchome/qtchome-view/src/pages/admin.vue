<template>
  <div class=" fillcontain">
    <page-head ref="head" v-bind:isMenu="isMenu" v-bind:isInput="isInput"></page-head>
    <div>
    <el-row>
      <el-col :span="3" >
        <div style="background: #475669">
        <el-tree
          class="filter-tree"
          :data="data2"
          :props="defaultProps"
          default-expand-all
          :filter-node-method="filterNode"
          :node-click="handleSelect"
          ref="tree2" >
        </el-tree>
        </div>
      </el-col>
      <el-col :span="21" >
        <router-view ></router-view>
      </el-col>
    </el-row>
    </div>

    <div style=" position: fixed;
    width: 100%;
    bottom: 0px;">
      <page-footer></page-footer>
    </div>
  </div>
</template>

<script>
    export default {
      name: 'admin',
      watch: {
        filterText (val) {
          this.$refs.tree2.filter(val)
        }
      },
      methods: {
        filterNode (value, data) {
          if (!value) return true
          return data.label.indexOf(value) !== -1
        },
        handleSelect (data) {
          console.log(data)
        }

      },
      data () {
        return {
          filterText: '',
          isMenu: false,
          isInput: false,
          data2: [{
            id: 1,
            label: '系统管理',
            children: [{
              id: 4,
              label: '用户管理',
              name: 'userManag'
            }]
          }, {
            id: 2,
            label: '一级 2',
            children: [{
              id: 5,
              label: '二级 2-1'
            }, {
              id: 6,
              label: '二级 2-2'
            }]
          }, {
            id: 3,
            label: '一级 3',
            children: [{
              id: 7,
              label: '二级 3-1'
            }, {
              id: 8,
              label: '二级 3-2'
            }]
          }],
          defaultProps: {
            children: 'children',
            label: 'label'
          }
        }
  }
    }
</script>

<style scoped>

</style>
