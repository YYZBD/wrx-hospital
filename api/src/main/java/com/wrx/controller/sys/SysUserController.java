package com.wrx.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wrx.common.base.BaseController;
import com.wrx.common.lang.Result;
import com.wrx.entity.User;
import com.wrx.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

	@Resource
	UserService userService;

	@GetMapping("/page")
	public Result page(User user) {
		Page<User> pageData = userService.page(getPage(), queryLike(user));
		return Result.success(pageData);
	}

	@GetMapping("/info/{id}")
	public Result info(@PathVariable long id) {
		User user = userService.getById(id);
		return Result.success(user);
	}
}
