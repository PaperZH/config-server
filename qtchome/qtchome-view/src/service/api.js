import request from '@/utils'
export function login (username, password) {
  return request({
    url: base + '/api-home/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}
export function logout (userId) {
  return request({
    url: base + '/api-home/logout',
    method: 'get',
    params: {
      userId
    }
  })
}
const base = 'http://localhost:8002'
export function get (url, data) {
  url = base + url
  return request({
    url: url,
    method: 'get',
    params: data
  })
}
export function post (url, data) {
  url = base + url
  return request({
    url: url,
    method: 'post',
    data: data
  })
}

export function remove (url, data) {
  url = base + url
  return request({
    url: url,
    method: 'delete',
    data: data
  })
}

export function put (url, data) {
  url = base + url
  return request({
    url: url,
    method: 'put',
    data: data
  })
}
