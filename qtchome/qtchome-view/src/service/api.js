import axios from 'axios'
import qs from 'qs'
import { getDynamicUrl, cookies } from '@/utils'
import vue from 'vue'
const baseUrl = getDynamicUrl('http://localhost:8081')
const request = (url, body, type = 'get', isJson = false, baseUrlRewrite) => {
  const query = {
    url: `${baseUrlRewrite || baseUrl}${url}`,
    method: type,
    withCredentials: true,
    timeout: 30000
  }
  if (type === 'get') {
    query.params = body
  } else {
    query.data = isJson ? body : qs.stringify(body)
  }
  return axios.request(query)
    .then(res => {
      return new Promise((resolve, reject) => {
        if (!res.data) {
          reject(new Error('服务器响应超时'))
          return
        }
        if (res.data.status === 0) {
          resolve(res.data.re)
        } else {
          res.data.message = res.data.msg
          reject(res.data)
        }
      })
    }, e => {
      switch (e.response.status) {
        case 401: // 未登录跳转至登录页
          if (cookies.getCookie('ucarincLogoutUrl')) {
            const ucarincLogoutUrl = cookies.getCookie('ucarincLogoutUrl')
            location.href = ucarincLogoutUrl
            cookies.delCookie('ucarincLogoutUrl')
          } else {
            top.window.postMessage({
              type: 'NO_LOGIN',
              msg: '401'
            }, '*')
            location.assign(getDynamicUrl('//yccadmin.alspark.cn/#/login'))
          }
          return Promise.reject(new Error('登录超时，请重新登录'))
        case 403: // 无权限操作
          top.window.postMessage({
            type: 'NO_PERMISSION',
            msg: '403'
          }, '*')
          return Promise.reject(new Error('无权访问此资源'))
        default:
          break
      }
      return Promise.reject(e.response)
    })
    .catch(e => {
      vue.prototype.$message.error(e && e.message ? e.message : '系统错误')
      return Promise.reject(e)
    })
}
export const indexApi = {
  login: (body) => request('/system/login.do_', body, 'post'),
  logOut: (query) => request('/system/logout.do_', query),
  getNavs: (query) => request('/system/menu/getModules.do_', query),
  getMenusByModule: (body) => request('/system/menu/getMenusByModule.do_', body, 'post')
}
export const authority = {
  getTree: (query) => request('/organization/authority/getAuthorityTree.do_', query),
  getDetail: (body) => request('/organization/authority/queryAuthority.do_', body, 'post'),
  add: (body) => request('/organization/authority/new.do_', body, 'post'),
  update: (body) => request('/organization/authority/edit.do_', body, 'post'),
  del: (body) => request('/organization/authority/delete.do_', body, 'post'),
  getAuthEmployee: (query) => request('/organization/authorityAssign/getAssignedUserByCode.do_', query),
  getEmployList: (query) => request('/organization/authorityAssign/empSearchForAuthAssign.do_', query),
  revokeEmployee: (body) => request('/organization/authorityAssign/cancelAssign2User.do_', body, 'post'),
  addAuthEmployee: (body) => request('/organization/authorityAssign/addAssign2User.do_', body, 'post')
}
export const department = {
  getBaseData: (query) => request('/organization/department/newInitData.do_', query),
  getBaseDateByEdit: (query) => request('/organization/department/editInitData.do_', query),
  getTree: (query) => request('/organization/department/deptTreeList.do_', query),
  assignParent: (body) => request('/organization/department/modifyNode.do_', body, 'post'),
  addDepartment: (body) => request('/organization/department/new.do_', body, 'post'),
  updateDepartment: (body) => request('/organization/department/edit.do_', body, 'post'),
  getDetail: (query) => request('/organization/department/queryDept.do_', query),
  removeDepartment: (body) => request('/organization/department/delete.do_', body, 'post')
}
export const employee = {
  getBaseData: (query) => request('/organization/employee/queryInitData.do_', query),
  getList: (query) => request('/organization/employee/search.do_', query),
  updateEmployee: (body) => request('/organization/employee/edit.do_', body, 'post'),
  getDetail: (query) => request('/organization/employee/queryEmp.do_', query),
  removeEmployee: (body) => request('/organization/employee/delete.do_', body, 'post'),
  unfreezeEmployee: (body) => request('/organization/employee/unlock.do_', body, 'post'),
  recoverEmployee: (body) => request('/organization/employee/recover.do_', body, 'post'),
  getOwnDepartment: (query) => request('/organization/empAuthority/getUserAssignedDept.do_', query),
  getOwnAuth: (query) => request('/organization/empAuthority/getUserAssignedAuth.do_', query),
  assignDepartment: (body) => request('/organization/empAuthority/assignDept2User.do_', body, 'post'),
  assignAuth: (body) => request('/organization/empAuthority/assignAuth2User.do_', body, 'post')
}
export const role = {
  getList: (query) => request('/organization/roles/search.do_', query),
  addRole: (body) => request('/organization/roles/new.do_', body, 'post'),
  updateRole: (body) => request('/organization/roles/edit.do_', body, 'post'),
  getDetail: (query) => request('/organization/roles/queryRole.do_', query),
  checkDel: (query) => request('/organization/roles/checkDel.do_', query),
  deleteRole: (query) => request('/organization/roles/delete.do_', query),
  getOwnAuth: (query) => request('/organization/rolesAuthority/getRoleAuth.do_', query),
  assignAuth: (body) => request('/organization/rolesAuthority/assignAuth2Role.do_', body, 'post'),
  getRoleEmployee: (query) => request('/organization/rolesAssign/getAssignedUserByRole.do_', query),
  getEmployList: (query) => request('/organization/rolesAssign/empSearchForRoleAssign.do_', query),
  revokeEmployee: (body) => request('/organization/rolesAssign/deleteRole2User.do_', body, 'post'),
  addRoleEmployee: (body) => request('/organization/rolesAssign/addRole2User.do_', body, 'post')
}
export const schedule = {
  getList: (query) => request('/system/schedulePlan/list.do_', query),
  add: (body) => request('/system/schedulePlan/new.do_', body, 'post'),
  update: (body) => request('/system/schedulePlan/edit.do_', body, 'post'),
  getDetail: (query) => request('/system/schedulePlan/querySchedule.do_', query),
  disable: (query) => request('/system/schedulePlan/disableSchedulePlan.do_', query),
  start: (query) => request('/system/schedulePlan/startSchedulePlan.do_', query),
  autoStart: (query) => request('/system/schedulePlan/startSchedulePlanForSave.do_', query),
  load: (query) => request('/system/schedulePlan/loadSchedulePlan.do_', query),
  getRetryList: (query) => request('/system/schedulePlanRetry/list.do_', query),
  excute: (query) => request('/system/schedulePlanRetry/doSchedulePlanRetry.do_', query)
}
export const sms = {
  getList: (query) => request('/system/sms/list.do_', query, 'post'),
  sendSms: (query) => request('/system/sms/doSend.do_', query, 'post')
}

export const log = {
  getList: (query) => request('/system/log/list.do_', query)
}

export const permission = {
  getPermissionForButton: (query) => {
    return request('/admin/common/security/getUserButtonAuth.do_', query)
      .then(re => {
        return Promise.resolve(re.data.split(','))
      })
  }
}
