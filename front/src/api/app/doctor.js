import request from '@/utils/userRequest'

export function ListDoctor(params) {
    return request({
        url: '/app/doctor/list',
        method: 'get',
        params: params
    })
}