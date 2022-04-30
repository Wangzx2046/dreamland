import request from '@/utils/request'

// 查询试用商品列表
export function listJdGoods(query) {
  return request({
    url: '/jdTry/jdGoods/list',
    method: 'get',
    params: query
  })
}

// 查询试用商品详细
export function getJdGoods(id) {
  return request({
    url: '/jdTry/jdGoods/' + id,
    method: 'get'
  })
}

// 新增试用商品
export function addJdGoods(data) {
  return request({
    url: '/jdTry/jdGoods',
    method: 'post',
    data: data
  })
}

// 修改试用商品
export function updateJdGoods(data) {
  return request({
    url: '/jdTry/jdGoods',
    method: 'put',
    data: data
  })
}

// 删除试用商品
export function delJdGoods(id) {
  return request({
    url: '/jdTry/jdGoods/' + id,
    method: 'delete'
  })
}

// 导出试用商品
export function exportJdGoods(query) {
  return request({
    url: '/jdTry/jdGoods/export',
    method: 'get',
    params: query
  })
}

