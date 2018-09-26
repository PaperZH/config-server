import * as API from './'

export default {
  getCourseList: params => {
    return API.POST(`/api-admin/course/getCourseList`, params)
  },
  update: params =>{
    return API.PUT('/api-admin/course',params)
  },
  remove:params =>{
    return API.DELETE('/api-admin/course',params)
  }
}
