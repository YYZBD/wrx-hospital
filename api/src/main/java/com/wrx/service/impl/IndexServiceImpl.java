package com.wrx.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrx.entity.Department;
import com.wrx.entity.Doctor;
import com.wrx.service.DepartmentService;
import com.wrx.service.DoctorService;
import com.wrx.service.IndexService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    // 科室信息
    @Resource
    DepartmentService departmentService;

    // 医生信息
    @Resource
    DoctorService doctorService;


    @Override
    public Object getHome() {

        // 获取科室信息
        List< Department> departments = departmentService.getTree();

        // 根据ID获取手术科室信息
        Department department = departmentService.getById(1L);

        // 获取手术科室下的子科室里所有的医生信息
        List<Doctor> doctors = doctorService.list(new QueryWrapper<Doctor>().inSql("department_id", "SELECT id FROM `app_department` WHERE parent_id = 1"));

        // 返回手术科室、手术科室所属的子科室，子科室里面的所有医生
        return MapUtil.builder().put("departments",departments).put("department",department).put("doctors",doctors).build();
    }
}
