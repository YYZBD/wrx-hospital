package com.wrx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrx.common.lang.Result;
import com.wrx.entity.User;
import com.wrx.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class TestController {

	@Resource
	UserService userService;

	// @Login
	@GetMapping("/test")
	public Result test() {
		return Result.success(userService.list(new QueryWrapper<User>().eq("id", 1)));
	}

}
