import * as API from './'

export default {
  banners: params => {
    return API.GET('/api-admin/reCourse', params)
  },
  add: params => {
    return API.POST(`/api-admin/reCourse`, params)
  },
  update: params =>{
    return API.PUT('/api-admin/reCourse',params)
  },
  remove:params =>{
    return API.DELETE('/api-admin/reCourse',params)
  }
}
