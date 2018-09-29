import axios from 'axios'
import {
  bus
} from './bus'

// 创建axios实例
const service = axios.create({
  timeout: 60000, // 请求超时时间
  withCredentials: true
})
// request拦截器
service.interceptors.request.use(config => {
  if (window.sessionStorage.getItem('access-token')) {
    config.headers.Authorization = window.sessionStorage.getItem('access-token')
  }
  return config
}, error => {
  // Do something with request error
  Promise.reject(error)
})
// 添加一个响应拦截器
service.interceptors.response.use(function (response) {
  if (response.data && response.data.code) {
    if (parseInt(response.data.code) === 401) {
      sessionStorage.removeItem('access-token')
      sessionStorage.removeItem('access-menus')
      sessionStorage.removeItem('access-userinfo')
      bus.$emit('headRefresh')
    }
  }
  return response
}, function (error) {
  return Promise.reject(error)
})

export default service
