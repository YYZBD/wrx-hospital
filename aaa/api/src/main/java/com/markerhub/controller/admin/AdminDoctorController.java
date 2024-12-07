package com.markerhub.controller.admin;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.base.BaseController;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Doctor;
import com.markerhub.service.DoctorService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/doctor")
public class AdminDoctorController extends BaseController {

	@Resource
    DoctorService doctorService;

	@GetMapping("/page")
	public Result page(Doctor doctor) {
		Page<Doctor> pageData = doctorService.page(getPage(), queryLike(doctor));
		return Result.success(pageData);
	}

	@GetMapping("/info/{id}")
	public Result info(@PathVariable long id) {
        Doctor doctor =doctorService.getById(id);
		return Result.success(doctor);
	}

	@PostMapping("/save")
	public Result save(@Validated @RequestBody Doctor doctor) {
		if (doctor.getId() == null) {
            doctor.setCreateTime(LocalDateTime.now());
		}
		doctorService.saveOrUpdate(doctor);
		return Result.success();
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Long[] ids) {
		Assert.isTrue(ArrayUtil.isNotEmpty(ids), "请选择删除选项");
		doctorService.removeBatchByIds(List.of(ids));
		return Result.success();
	}
}
