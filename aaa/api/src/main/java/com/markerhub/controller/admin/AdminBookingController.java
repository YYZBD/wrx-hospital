package com.markerhub.controller.admin;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.base.BaseController;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Booking;
import com.markerhub.service.BookingService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/booking")
public class AdminBookingController extends BaseController {

	@Resource
    BookingService bookingService;

	@GetMapping("/page")
	public Result page(Booking booking) {
		Page<Booking> pageData = bookingService.page(getPage(), queryLike(booking));
		return Result.success(pageData);
	}

	@GetMapping("/info/{id}")
	public Result info(@PathVariable long id) {
        Booking booking =bookingService.getById(id);
		return Result.success(booking);
	}

	@PostMapping("/save")
	public Result save(@Validated @RequestBody Booking booking) {
		if (booking.getId() == null) {
            booking.setCreateTime(LocalDateTime.now());
		}
		bookingService.saveOrUpdate(booking);
		return Result.success();
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Long[] ids) {
		Assert.isTrue(ArrayUtil.isNotEmpty(ids), "请选择删除选项");
		bookingService.removeBatchByIds(List.of(ids));
		return Result.success();
	}
}
