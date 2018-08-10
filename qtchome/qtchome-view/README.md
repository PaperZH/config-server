# qtcollege-fe

> 优车课程前端项目

## Build Setup

``` bash

# Vue 环境安装可参考wiki
http://wiki.10101111.com/display/WUHANTECHCENTER/VueJs

# cmd 切换目录到 qtcollege-fe
npm install @ucar/ucar-vue --registry=http://r.npm.10101111.com

#
npm install-g cnpm --registry=https://registry.npm.taobao.org  
cnpm install

# serve with hot reload at localhost:8081
npm run dev

# 开发页面参考手册
# src/pages/content 前端页面目录
#   home.vue           首页轮播图和课程列表
#   course.vue         课程列表
#   details.vue        课程详细
#   user.vue           个人主页 
# src/pages/content/info 个人信息页面相关组件
#   addCourse.vue       添加课件
#   drawupplan.vue      制定计划
#   favorite.vue        我的收藏
#   myplan.vue          我的计划
#   release.vue         发布课程
#   releasecourse.vue   我发布的课程
#   userInfo.vue        我的资料
#     content/dialog
#	   dialogplan.vue      添加计划弹框功能
#  src/pages
#       admin.vue        后台管理首页框架
#		index.vue            前端首页框架
#   
#
## src/pages/components  前端组件 如：页面头/脚/弹框组件
#    head.vue            页头
#	   footer.vue          页尾
#    breadcrumb.vue      课程页面的面包屑
#	   courselist.vue      课程列表(注：课程页面与首页使用同一列表)

#src/pages/management    后端管理界面

#src/router
# index.js               页面路由
 
#src/css                 页面样式

#src/service             服务请求



# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
