import request from '@/utils/adminRequest'

export function GetPage(params) {
	return request({
		url: '/admin/schedule/page',
		method: 'get',
		params: params
	})
}

export function Info(id) {
	return request({
		url: '/admin/schedule/info/' + id,
		method: 'get'
	})
}

export function Save(data) {
	return request({
		url: '/admin/schedule/save',
		method: 'post',
		data
	})
}

export function Delete(ids) {
	return request({
		url: '/admin/schedule/delete',
		method: 'post',
		data: ids
	})
}