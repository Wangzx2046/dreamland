import request from '@/utils/request'

// 查询活动信息列表
export function listInfo(query) {
  return request({
    url: '/act/info/list',
    method: 'get',
    params: query
  })
}

// 查询活动信息详细
export function getInfo(actId) {
  return request({
    url: '/act/info/' + actId,
    method: 'get'
  })
}

// 新增活动信息
export function addInfo(data) {
  return request({
    url: '/act/info',
    method: 'post',
    data: data
  })
}

// 修改活动信息
export function updateInfo(data) {
  return request({
    url: '/act/info',
    method: 'put',
    data: data
  })
}

// 删除活动信息
export function delInfo(actId) {
  return request({
    url: '/act/info/' + actId,
    method: 'delete'
  })
}

// 导出活动信息
export function exportInfo(query) {
  return request({
    url: '/act/info/export',
    method: 'get',
    params: query
  })
}

