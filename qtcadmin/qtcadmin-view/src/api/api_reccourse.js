import * as API from './'

export default {
  getRecCourse: params => {
    return API.POST('/api-admin/recCourse', params)
  },
  getCourse: params => {
    return API.POST('/api-admin/recCourse/getCourse', params)
  },
  add: params => {
    return API.POST(`/api-admin/recCourse/save`, params)
  },
  update: params =>{
    return API.POST('/api-admin/recCourse/save',params)
  },
  remove:params =>{
    return API.DELETE('/api-admin/recCourse',params)
  },
  batchRemove:params =>{
    return API.DELETE('/api-admin/recCourse',params)
  }
}
