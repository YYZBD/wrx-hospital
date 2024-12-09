package com.wrx.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrx.common.lang.Result;
import com.wrx.entity.Doctor;
import com.wrx.service.DoctorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/app/doctor")
public class DoctorController {


    // 首页医生信息封装
    @Resource
    DoctorService doctorService;

    /**
     * 首页
     * 医生信息内容请求接口
     * @return
     */
    @GetMapping("/list")
    public Result list(Long departmentId) {

        // 展示该科室下所有医生，包括子科室下所有医生
        List<Doctor> doctors = doctorService.list(new QueryWrapper<Doctor>()
                .eq(departmentId != null, "department_id", departmentId));

        List<Doctor> childDoctors = doctorService.list(new QueryWrapper<Doctor>()
                .inSql("department_id", "SELECT id FROM `app_department` WHERE parent_id =" + departmentId));

        doctors.addAll(childDoctors);
        return Result.success(doctors);
    }

}
