import { login, logout, get, post, remove, put } from '@/service/api'
export const user = {
  
  state: {
    token: '',
    userId: 100,
    name: '',
    avatar: '',
    addRouters: [],
    perms: []
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_USERID: (state, userId) => {
      state.userId = userId
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_MENU: (state, routers) => {
      state.addRouters = routers
     //  state.routers = constantRouterMap.concat(routers)
    },
    SET_PERMS: (state, routers) => {
      state.perms = routers
     //  state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    Login: function ({commit}, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password).then(result => {
           resolve(result)
        }).catch(error => {
          reject(error)
        })
      })
    },

    Logout: function ({commit}, param) {
      console.log(param)
      return new Promise((resolve, reject) => {
        logout(param.userId).then(response => {
          const data = response.data
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // get 请求
    Get: function ({commit, state}, param) {
      return new Promise((resolve, reject) => {
        get(param.url, param.data).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // post 请求
    Post: function ({commit, state}, param) {
      return new Promise((resolve, reject) => {
        post(param.url, param.data).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // put 请求
    Put: function ({commit, state}, param) {
      return new Promise((resolve, reject) => {
        put(param.url, param.data).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // delete 请求
    Delete: function ({commit, state}, param) {
      return new Promise((resolve, reject) => {
        remove(state.token, param.url, param.data).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export const getters = {
  token: state => state.token,
  avatar: state => state.avatar,
  userId: state => state.userId,
  name: state => state.name,
  menu: state => state.menu,
  addRouters: state => state.addRouters,
  perms: state => state.perms
}
