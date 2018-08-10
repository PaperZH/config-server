// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'core-js/fn/array/from'
import 'core-js/fn/object/assign'
import 'core-js/es6/promise'
import Vue from 'vue'
import App from './App'
import Router from 'vue-router'
import router from './router'
import store from './store/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'
import './css/study.css'
import './icons' // icon
import { UcarPagination, Permission } from '@ucar/ucar-vue'
import Head from '@/pages/components/head'  // 头部
import Footer from '@/pages/components/footer' // 底部
import CourseList from '@/pages/components/courselist' // 课程列表
import Breadcrumb from '@/pages/components/breadcrumb' //  面包屑 导航
import dialogplan from '@/pages/content/dialog/dialogplan'  // 添加计划弹框

Vue.use(Router)
Vue.use(ElementUI)
Vue.use(UcarPagination)
Vue.use(Permission)
Vue.component('page-head', Head) // 注册头部组件
Vue.component('page-footer', Footer) // 注册页面底部组件
Vue.component('course-ist', CourseList) // 注册课件列表组件
Vue.component('tabs-breadcrumb', Breadcrumb) // 面包屑组件
Vue.component('dialog-plan', dialogplan) // 注册制定计划弹框组件
/**
 * element-ui hack
 */
Vue.prototype.$message.success = (str) => {
  Vue.prototype.$message({
    type: 'success',
    message: str,
    showClose: true
  })
}
Vue.prototype.$message.warning = (str) => {
  Vue.prototype.$message({
    type: 'warning',
    message: str,
    showClose: true
  })
}
Vue.prototype.$message.error = (str) => {
  Vue.prototype.$message({
    type: 'error',
    message: str,
    showClose: true
  })
}
// register globally
Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
