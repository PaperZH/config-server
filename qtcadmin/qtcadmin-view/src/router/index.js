import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Dashboard from '@/components/Dashboard'

import RoleList from '@/components/role/list'

import UserList from '@/components/user/list'
import UserProfile from '@/components/user/profile'

import MenuList from '@/components/menu/list'

import FileList from '../components/file/List.vue'

import LogList from '../components/log/List'

import DeptList from '../components/dept/list'

import EurekaList from '../components/monitor/eureka/list'

import DruidList from '../components/monitor/druid/list'

import Swagger2List from '../components/monitor/api/list'

import BannerList from '../components/recommand/bannerRecommand'

import RecCourseList from '../components/recommand/courseRecommand'

import CourseList from '../components/course/course'

import CoursewareList from '../components/course/courseware'

// 懒加载方式，当路由被访问的时候才加载对应组件
const Login = resolve => require(['@/components/Login'], resolve)

Vue.use(Router)

let router = new Router({
  routes: [
    {
      path: '/login',
      name: '登录',
      component: Login
    },
    {
      path: '/',
      name: 'home',
      component: Home,
      redirect: '/dashboard',
      leaf: true, // 只有一个节点
      menuShow: true,
      iconCls: 'fa fa-home', // 图标样式class
      children: [
        {path: '/dashboard', component: Dashboard, name: '首页', menuShow: true}
      ]
    },
    {
      path: '/',
      component: Home,
      name: '用户管理',
      menuShow: true,
      leaf: true, // 只有一个节点
      iconCls: 'fa fa-user', // 图标样式class
      children: [
        {path: '/admin/user', component: UserList, name: '用户列表', menuShow: true}
      ]
    },
    {
      path: '/',
      component: Home,
      name: '菜单管理',
      menuShow: true,
      leaf: true, // 只有一个节点
      iconCls: 'fa fa-server', // 图标样式class
      children: [
        {path: '/admin/menu', component:MenuList, name: '菜单列表', menuShow: true}
      ]
    },
    {
      path: '/',
      component: Home,
      name: '部门管理',
      menuShow: true,
      leaf: true, // 只有一个节点
      iconCls: 'fa fa-star', // 图标样式class
      children: [
        {path: '/admin/dept', component: DeptList, name: '部门列表', menuShow: true}
      ]
    },
    {
      path: '/',
      component: Home,
      name: '角色管理',
      menuShow: true,
      leaf: true,
      iconCls: 'fa fa-group',
      children: [
        {path: '/admin/role', component: RoleList, name: '角色管理', menuShow: true},
      ]
    },

    {
      path: '/',
      component: Home,
      name: '文件管理',
      menuShow: true,
      leaf: true,
      iconCls: 'fa fa-group',
      children: [
        {path: '/admin/file', component: FileList, name: '文件管理', menuShow: true},
      ]
    },
    {
      path: '/',
      component: Home,
      name: '日志管理',
      menuShow: true,
      leaf: true,
      iconCls: 'fa fa-group',
      children: [
        {path: '/base/log', component: LogList, name: '日志管理', menuShow: true}
      ]
    },
    {
      path: '/',
      component: Home,
      name: '个人信息',
      menuShow: true,
      iconCls: 'iconfont icon-setting1',
      children: [
        {path: '/user/profile', component: UserProfile, name: '个人信息', menuShow: true}
      ]
    },

    {
      path: '/',
      component: Home,
      name: '注册服务',
      menuShow: true,
      iconCls: 'iconfont icon-setting1',
      children: [
        {path: '/monitor/eureka',component: EurekaList, name: '服务监控', menuShow: true},
      ]
    },
    {
      path: '/',
      component: Home,
      name: 'SQL监控',
      menuShow: true,
      iconCls: 'iconfont icon-setting1',
      children: [
        {path: '/monitor/druid',component: DruidList, name: 'SQL监控', menuShow: true},
      ]
    },
    {
      path: '/',
      component: Home,
      name: 'API调用',
      menuShow: true,
      leaf: true,
      iconCls: 'fa fa-group',
      children: [
        {path: '/monitor/api', component: Swagger2List, name: 'API调用', menuShow: true}
      ]
    },
    {
      path: '/',
      component: Home,
      name: '推荐管理',
      menuShow: true,
      iconCls: 'iconfont icon-setting1',
      children: [
        {path: '/admin/banner',component: BannerList, name: 'banner推荐', menuShow: true},
        {path: '/admin/recCourse',component: RecCourseList, name: '课程推荐', menuShow: true}
      ]
    },
    {
      path: '/',
      component: Home,
      name: '课程管理',
      menuShow: true,
      iconCls: 'iconfont icon-setting1',
      children: [
        {path: '/admin/course',component: CourseList, name: '课程管理', menuShow: true},
        {path: '/admin/courseware',component: CoursewareList, name: '课件管理', menuShow: true}
      ]
    },
  ]
})

router.beforeEach((to, from, next) => {
  // console.log('to:' + to.path)
  if (to.path.startsWith('/login')) {
    window.localStorage.removeItem('access-token')
    //window.localStorage.removeItem('access-user')
    next()
  } else {
    //let user = JSON.parse(window.localStorage.getItem('access-token'))
    let user = window.localStorage.getItem('access-token');
    if (!user) {
      next({path: '/login'})
    } else {
      next()
    }
  }
})

export default router
