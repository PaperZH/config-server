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
import admin from '@/pages/admin'

const router = new Router({
  routes: [{
    path: '/admin',
    component: admin
  },
  {
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
        component: userInfo
      }, {
        path: '/release',
        name: 'release',
        component: release
      }, {
        path: '/favorite',
        name: 'favorite',
        component: favorite
      }, {
        path: '/addCourse',
        name: 'addCourse',
        component: addCourse
      }, {
        path: '/myplan',
        name: 'myplan',
        component: myplan
      }, {
        path: '/drawupplan',
        name: 'drawupplan',
        component: drawupplan
      }, {
        path: '/releasecourse',
        name: 'releasecourse',
        component: releasecourse
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
  next()                                                                          // 如果匹配到正确跳转
})

export default router
