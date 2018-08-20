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
Vue.use(Router)
Vue.use(ElementUI)
Vue.use(UcarPagination)
Vue.use(Permission)

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
