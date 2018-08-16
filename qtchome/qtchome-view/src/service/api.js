import request from '@/utils'

export function login (username, password) {
  return request({
    url: '/api/authentication/form/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}
export function get (url, data) {
  return request({
    url: url,
    method: 'get',
    data: data
  })
}
export function post (url, data) {
  return request({
    url: url,
    method: 'post',
    data: data
  })
}

export function remove (url, data) {
  return request({
    url: url,
    method: 'delete',
    data: data
  })
}

export function put (url, data) {
  return request({
    url: url,
    method: 'put',
    data: data
  })
}
