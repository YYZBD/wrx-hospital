package com.wrx.controller.app;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrx.common.annotation.Login;
import com.wrx.common.lang.Result;
import com.wrx.entity.Doctor;
import com.wrx.entity.MedicalCard;
import com.wrx.entity.Schedule;
import com.wrx.security.StpKit;
import com.wrx.service.DepartmentService;
import com.wrx.service.DoctorService;
import com.wrx.service.MedicalCardService;
import com.wrx.service.ScheduleService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/app/booking")
public class BookingController {


    // 首页医生信息封装
    @Resource
    DoctorService doctorService;

    // 获取可预约列表
    @Resource
    ScheduleService scheduleService;

    // 科室信息
    @Resource
    DepartmentService departmentService;

    // 就诊卡信息
    @Resource
    MedicalCardService medicalCardService;

    /**
     * 首页
     * 医生信息内容请求接口
     * @return
     */
    @GetMapping("/departments")
    public Result list() {
        // 返回可以预约的科室
        return Result.success(departmentService.getTree());
    }

    /**
     * 医生信息内容请求接口
     * @return 返回可预约医生
     * @param departmentId 根据可选科室进行条件筛选
     * @param scheduleDate 根据可预约日期进行筛选
     */
    @GetMapping("/schedule-doctors")
    public Result scheduleDoctors(Long departmentId, LocalDate scheduleDate) {

        List<Doctor> doctors = doctorService.listBySchedule(new QueryWrapper<Schedule>()
                .eq(departmentId != null, "s.department_id", departmentId)
                .eq(scheduleDate != null, "s.schedule_date", scheduleDate));
        // 返回可以预约的医生
        return Result.success(doctors);
    }

    /**
     * 点击预约后，获取当前医生的排班信息和该用户的就诊卡信息
     * @return 返回=
     * @param doctorId 根据医生id获取信息
     */
    @Login
    @GetMapping("/schedule-doctor")
    public Result scheduleDoctors(@Nonnull Long doctorId) {

        // 根据登录用户的id获取当前用户就诊卡列表
        List<MedicalCard> medicalCards = medicalCardService.list(new QueryWrapper<MedicalCard>().eq("user_id", StpKit.getUserId()));

        // 根据当前医生ID获取该医生排班时间
        List<Schedule> schedules = scheduleService.list(new QueryWrapper<Schedule>().eq("doctor_id", doctorId));

        return Result.success(MapUtil.builder().put("medicalCards", medicalCards).put("schedules", schedules).build());
    }
}
