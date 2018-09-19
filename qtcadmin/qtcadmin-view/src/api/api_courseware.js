import * as API from './'

export default {
  getCoursewareList: params => {
    return API.GET(`/api-admin/courseware/getCoursewareList`, params)
  },
  update: params =>{
    return API.PUT('/api-admin/courseware',params)
  },
  remove:params =>{
    return API.DELETE('/api-admin/courseware',params)
  }
}
