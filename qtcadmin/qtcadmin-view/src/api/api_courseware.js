import * as API from './'

export default {
  getCoursewareList: params => {
    return API.POST(`/api-admin/courseware/getCoursewareList`, params)
  },
  update: params =>{
    return API.PUT('/api-admin/courseware',params)
  },
  remove:params =>{
    return API.DELETE('/api-admin/courseware',params)
  }
}
