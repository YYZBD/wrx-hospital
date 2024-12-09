import request from '@/utils/userRequest'

export function GetHome() {
    return request({
        url: '/app/home',
        method: 'get',
    })
}