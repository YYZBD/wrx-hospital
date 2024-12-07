package com.markerhub.controller.admin;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.base.BaseController;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Department;
import com.markerhub.service.DepartmentService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/department")
public class AdminDepartmentController extends BaseController {

	@Resource
    DepartmentService departmentService;

	@GetMapping("/page")
	public Result page(Department department) {
		Page<Department> pageData = departmentService.page(getPage(), queryLike(department));
		return Result.success(pageData);
	}

	@GetMapping("/info/{id}")
	public Result info(@PathVariable long id) {
        Department department =departmentService.getById(id);
		return Result.success(department);
	}

	@PostMapping("/save")
	public Result save(@Validated @RequestBody Department department) {
		if (department.getId() == null) {
            department.setCreateTime(LocalDateTime.now());
		}
		departmentService.saveOrUpdate(department);
		return Result.success();
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Long[] ids) {
		Assert.isTrue(ArrayUtil.isNotEmpty(ids), "请选择删除选项");
		departmentService.removeBatchByIds(List.of(ids));
		return Result.success();
	}
}
