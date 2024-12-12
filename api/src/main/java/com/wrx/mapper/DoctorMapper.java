package com.wrx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wrx.entity.Doctor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wrx.entity.Schedule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 医生表 Mapper 接口
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
public interface DoctorMapper extends BaseMapper<Doctor> {

    // 根据MyBatis-Plus 的wrapper查询出医生列表
    @Select("SELECT distinct d.* from app_schedule s left join app_doctor d on d.id=s.doctor_id ${ew.customSqlSegment}")
    List<Doctor> listBySchedule(@Param(Constants.WRAPPER) QueryWrapper<Schedule> wrapper);
}
