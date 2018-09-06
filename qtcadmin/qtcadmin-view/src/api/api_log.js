/**
 * 日志相关的api
 */
import * as API from './'

export default {
  list: params => {
    return API.GET('/api-base/log', params)
  },
  remove: params => {
    return API.DELETE('/api-base/log', params)
  },
  batchRemove: params => {
    return API.POST('/api-base/log/batchRemove', params)
  }
}
