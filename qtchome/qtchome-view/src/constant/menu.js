export default [
  [
    {
      // 目录或具体路由的标题
      title: '个人客户管理',
      // icon根据element-ui的icon官方文档选择
      icon: 'el-icon-service',
      // path以_开头则表示这是一个目录path并不是路由的路径
      path: '_customer',
      // subMenus 代表子级菜单, 其中元素的字段名除icon外与父级相同
      subMenus: [
        {
          title: '客户信息',
          path: 'http://localhost:8080/#/customer/list'
        },
        {
          title: '新建客户',
          path: 'http://localhost:8080/#/customer/info',
          // hidden=true 代表不会在菜单中显示
          hidden: true
        },
        {
          title: '客户信息',
          // 动态路由示例
          path: 'http://localhost:8080/#/customer/info/:id',
          hidden: true
        }
      ]
    },
    {
      title: '会议账号管理',
      icon: 'el-icon-date',
      path: '_meeting',
      subMenus: [
        {
          title: '会议账号',
          path: 'http://localhost:8080/#/meeting/account'
        }
      ]
    }
  ],
  [
    {
      // 目录或具体路由的标题
      title: '订单管理',
      // icon根据element-ui的icon官方文档选择
      icon: 'el-icon-service',
      // path以_开头则表示这是一个目录path并不是路由的路径
      path: '_customer',
      // subMenus 代表子级菜单, 其中元素的字段名除icon外与父级相同
      subMenus: [
        {
          title: '内网订单',
          path: 'http://localhost:8080/#/customer/list'
        },
        {
          title: '订单查询',
          path: 'http://localhost:8080/#/customer/info',
          // hidden=true 代表不会在菜单中显示
          hidden: true
        },
        {
          title: '出租车订单查询',
          // 动态路由示例
          path: 'http://localhost:8080/#/customer/info/:id',
          hidden: true
        }
      ]
    },
    {
      title: '结算管理',
      icon: 'el-icon-date',
      path: '_meeting',
      subMenus: [
        {
          title: '结算单查询',
          path: 'http://localhost:8080/#/meeting/account'
        }
      ]
    }
  ]
]
