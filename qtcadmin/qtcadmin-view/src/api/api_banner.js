import * as API from './'

export default {
  banners: params => {
    return API.GET('/api-admin/banner', params)
  },
  getUrl:params =>{
    return API.GET('/api-admin/banner/getToken',params)
  },
  add: params => {
    return API.POST(`/api-admin/banner`, params)
  },
  update: params =>{
    return API.PUT('/api-admin/banner',params)
  },
  remove:params =>{
    return API.DELETE('/api-admin/banner',params)
  },
  uploadBanner:params=>{
    return API.POST('/api-admin/banner/upload',params)
  }
}
