import request from '@/utils/userRequest'

/**
 * 可预约科室列表
 * @returns {*}
 * @constructor
 */
export function ListDepartments() {
    return request({
        url: '/app/booking/departments',
        method: 'get',
    })
}

/**
 * 可预约医生列表
 * @param params
 * @returns {*}
 * @constructor
 */
export function ListDoctor(params) {
    return request({
        url: '/app/booking/schedule-doctors',
        method: 'get',
        params: params
    })
}

/**
 * 预约该医生排班信息和登录用户的就诊卡信息
 * @param doctorId 该医生的id
 * @returns {*}
 * @constructor
 */
export function GetDoctor(doctorId) {
    return request({
        url: '/app/booking/schedule-doctor',
        method: 'get',
        params: {
            doctorId: doctorId
        }
    })
}