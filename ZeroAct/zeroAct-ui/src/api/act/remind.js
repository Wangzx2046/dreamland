import request from '@/utils/request'

// 查询活动打卡记录列表
export function listRemind(query) {
  return request({
    url: '/act/remind/list',
    method: 'get',
    params: query
  })
}

// 查询活动打卡记录详细
export function getRemind(remindId) {
  return request({
    url: '/act/remind/' + remindId,
    method: 'get'
  })
}

// 新增活动打卡记录
export function addRemind(data) {
  return request({
    url: '/act/remind',
    method: 'post',
    data: data
  })
}

// 修改活动打卡记录
export function updateRemind(data) {
  return request({
    url: '/act/remind',
    method: 'put',
    data: data
  })
}

// 删除活动打卡记录
export function delRemind(remindId) {
  return request({
    url: '/act/remind/' + remindId,
    method: 'delete'
  })
}

// 导出活动打卡记录
export function exportRemind(query) {
  return request({
    url: '/act/remind/export',
    method: 'get',
    params: query
  })
}

