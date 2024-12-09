package com.wrx.controller.sys;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrx.common.dto.LoginDto;
import com.wrx.common.dto.PasswordDto;
import com.wrx.common.dto.UserDto;
import com.wrx.common.lang.Result;
import com.wrx.entity.Admin;
import com.wrx.security.StpKit;
import com.wrx.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sys")
public class SysLoginController {

	@Resource
	AdminService adminService;

	@PostMapping("/login")
	public Result login(@Validated @RequestBody LoginDto loginDto) {

		Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("username", loginDto.getUsername()));
		if (admin == null || !admin.getPassword().equals(DigestUtil.sha256Hex(loginDto.getPassword()))) {
			return Result.fail("用户名或密码错误");
		}

		admin.setLastLoginTime(LocalDateTime.now());
		adminService.updateById(admin);

		StpKit.ADMIN.login(admin.getId());
		String token = StpKit.ADMIN.getTokenValue();

		return Result.success(MapUtil.builder()
				.put("adminToken", token)
				.put("adminInfo", BeanUtil.toBean(admin, UserDto.class))
				.build());
	}

	@GetMapping("/logout")
	public Result logout() {
		StpKit.ADMIN.logout();
		return Result.success();
	}

	@PostMapping("/updatePass")
	public Result updatePass(@RequestBody PasswordDto dto) {
		Admin admin = adminService.getById(StpKit.getAdminId());
		Assert.isTrue(admin.getPassword().equals(DigestUtil.sha256Hex(dto.getOldPass())), "旧密码不正确");

		admin.setPassword(DigestUtil.sha256Hex(dto.getNewPass()));
		adminService.updateById(admin);
		return Result.success();
	}
}
