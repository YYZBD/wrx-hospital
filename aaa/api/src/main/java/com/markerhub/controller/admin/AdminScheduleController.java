package com.markerhub.controller.admin;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.base.BaseController;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Schedule;
import com.markerhub.service.ScheduleService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/schedule")
public class AdminScheduleController extends BaseController {

	@Resource
    ScheduleService scheduleService;

	@GetMapping("/page")
	public Result page(Schedule schedule) {
		Page<Schedule> pageData = scheduleService.page(getPage(), queryLike(schedule));
		return Result.success(pageData);
	}

	@GetMapping("/info/{id}")
	public Result info(@PathVariable long id) {
        Schedule schedule =scheduleService.getById(id);
		return Result.success(schedule);
	}

	@PostMapping("/save")
	public Result save(@Validated @RequestBody Schedule schedule) {
		if (schedule.getId() == null) {
            schedule.setCreateTime(LocalDateTime.now());
		}
		scheduleService.saveOrUpdate(schedule);
		return Result.success();
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Long[] ids) {
		Assert.isTrue(ArrayUtil.isNotEmpty(ids), "请选择删除选项");
		scheduleService.removeBatchByIds(List.of(ids));
		return Result.success();
	}
}
