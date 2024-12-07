import request from '@/utils/adminRequest'

export function GetPage(params) {
	return request({
		url: '/admin/medicalRecord/page',
		method: 'get',
		params: params
	})
}

export function Info(id) {
	return request({
		url: '/admin/medicalRecord/info/' + id,
		method: 'get'
	})
}

export function Save(data) {
	return request({
		url: '/admin/medicalRecord/save',
		method: 'post',
		data
	})
}

export function Delete(ids) {
	return request({
		url: '/admin/medicalRecord/delete',
		method: 'post',
		data: ids
	})
}