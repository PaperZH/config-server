import axios from 'axios'
// import { Message, MessageBox } from 'element-ui'
// import store from '../store'
// import qs from 'qs'
// 创建axios实例
const service = axios.create({
  timeout: 5000, // 请求超时时间
  withCredentials: true
})
// request拦截器
service.interceptors.request.use(config => {
  // config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
  // if (config.method === 'get') {
  //   config.params = qs.stringify({
  //     ...config.params
  //   })
  // }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})
export default service
