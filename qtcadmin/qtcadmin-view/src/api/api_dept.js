/**
 * 部门相关的api
 */
import * as API from './'

export default {

  depts: params=>{
    return API.GET('/api-admin/dept',params)
  },
  editDept: params=>{
    return API.PUT('/api-admin/dept',params)
  },
  deptIdsByRoleId: params=>{
    return API.GET('/api-admin/dept/deptId',params)
  },
  addDept: params=>{
    return API.POST('/api-admin/dept',params)
  },
  removeDept: params=>{
    return API.DELETE('/api-admin/dept',params)
  }
}
