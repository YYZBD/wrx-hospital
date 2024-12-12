package com.wrx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrx.entity.Doctor;
import com.wrx.entity.Schedule;
import com.wrx.mapper.DoctorMapper;
import com.wrx.service.DoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 医生表 服务实现类
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    // 做关联查询，根据科室和排班日期查询可预约医生，所有用mapper
    @Resource
    DoctorMapper doctorMapper;

    @Override
    public List<Doctor> listBySchedule(QueryWrapper<Schedule> wrapper) {

        return doctorMapper.listBySchedule(wrapper);
    }
}
