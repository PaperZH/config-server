import Router from 'vue-router'
// import {mixin} from '@/utils'
import index from '@/pages/index'
import home from '@/pages/content/home'
import course from '@/pages/content/course'
import details from '@/pages/content/details'
import construction from '@/pages/content/299'
import user from '@/pages/content/user'
import userInfo from '@/pages/content/info/userInfo'
import release from '@/pages/content/info/release'
import favorite from '@/pages/content/info/favorite'
import addCourse from '@/pages/content/info/addCourse'
import myplan from '@/pages/content/info/myplan'
import drawupplan from '@/pages/content/info/drawupplan'
import releasecourse from '@/pages/content/info/releasecourse'
import distributePlan from '@/pages/content/info/distributePlan'

const router = new Router({
  routes: [{
    path: '/',
    component: index,
    children: [ {
      path: '/home',
      name: 'home',
      component: home,
      meta: {
        keepalive: true
      }
    }, {
      path: '/course',
      name: 'course',
      component: course
    }, {
      path: '/user',
      name: 'user',
      component: user,
      children: [ {
        path: '/userInfo',
        name: 'userInfo',
        component: userInfo,
        meta: { title: '基本设置' }
      }, {
        path: '/release',
        name: 'release',
        component: release,
        meta: { title: '发布课程' }
      }, {
        path: '/favorite',
        name: 'favorite',
        component: favorite,
        meta: { title: '我的收藏' }
      }, {
        path: '/addCourse',
        name: 'addCourse',
        component: addCourse
      }, {
        path: '/myplan',
        name: 'myplan',
        component: myplan,
        meta: { title: '我的计划' }
      }, {
        path: '/drawupplan',
        name: 'drawupplan',
        meta: { title: '制定计划' },
        component: drawupplan
      }, {
        path: '/distributePlan',
        name: 'distributePlan',
        meta: {title: '发布计划'},
        component: distributePlan
      }, {
        path: '/releasecourse',
        name: 'releasecourse',
        component: releasecourse,
        meta: { title: '我发布的课程' }
      }]
    }, {
      path: 'details',
      name: 'details',
      component: details
    }, {
      path: 'summary',
      name: 'summary',
      component: construction
    }, {
      path: '/ask',
      name: 'ask',
      component: construction
    }
    ]
  }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {

  }
  next()                                                                    // 如果匹配到正确跳转
})

export default router
