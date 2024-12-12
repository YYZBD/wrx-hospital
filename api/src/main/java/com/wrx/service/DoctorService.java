package com.wrx.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrx.entity.Doctor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrx.entity.Schedule;

import java.util.List;

/**
 * <p>
 * 医生表 服务类
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
public interface DoctorService extends IService<Doctor> {

    List<Doctor> listBySchedule(QueryWrapper<Schedule> wrapper);
}
