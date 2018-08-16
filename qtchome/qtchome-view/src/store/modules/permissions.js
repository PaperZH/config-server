import { login, get, post, remove, put } from '@/service/api'
export const user = {
  state: {
    name: '',
    avatar: '',
    roles: [],
    addRouters: []
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_MENU: (state, routers) => {
      state.addRouters = routers
     //  state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    Login: function ({commit}, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password).then(response => {
          const data = response.data
          commit('SET_TOKEN', data)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // get 请求
    Get: function ({commit, state}, url, param) {
      return new Promise((resolve, reject) => {
        get(url, param).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // post 请求
    Post: function ({commit, state}, url, param) {
      return new Promise((resolve, reject) => {
        post(url, param).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // put 请求
    Put: function ({commit, state}, url, param) {
      return new Promise((resolve, reject) => {
        put(url, param).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // delete 请求
    Delete: function ({commit, state}, url, param) {
      return new Promise((resolve, reject) => {
        remove(state.token, url, param).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export const getters = {
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  addRouters: state => state.user.addRouters,
  routers: state => state.user.routers
}
