import * as API from './'

export default {
  files: params => {
    return API.GET('/api-admin/file', params)
  },
  getUrl:params =>{
    return API.GET('/api-admin/file/getToken',params)
  },
  add: params => {
    return API.POST(`/api-admin/file`, params)
  },
  update: params =>{
    return API.PUT('/api-admin/file',params)
  },
  remove:params =>{
    return API.DELETE('/api-admin/file',params)
  },
  uploadFile:params=>{
    return API.POST('/api-admin/file/upload',params)
  }
}
