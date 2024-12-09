package com.wrx.controller.app;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.wrx.common.annotation.Login;
import com.wrx.common.dto.LoginDto;
import com.wrx.common.dto.RegisterDto;
import com.wrx.common.dto.UserDto;
import com.wrx.common.lang.Result;
import com.wrx.security.StpKit;
import com.wrx.service.UserService;
import com.wrx.utils.LocalCache;
import com.wf.captcha.SpecCaptcha;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/app")
public class LoginController {

	@Resource
	UserService userService;

	@PostMapping("/login")
	public Result login(@Validated @RequestBody LoginDto dto) {

		UserDto userDto = userService.login(dto);
		StpKit.USER.login(userDto.getId());

		return Result.success(MapUtil.builder()
				.put("userToken", StpKit.USER.getTokenValue())
				.put("userInfo", userDto)
				.build()
		);
	}

	@Login
	@GetMapping("/logout")
	public Result logout() {
		StpKit.USER.logout();
		return Result.success();
	}

	@GetMapping("/captcha")
	public Result captcha() {
		SpecCaptcha specCaptcha = new SpecCaptcha(120, 36, 5);
		String verCode = specCaptcha.text().toLowerCase();

		String key = UUID.randomUUID().toString();
		LocalCache.set(key, verCode, 120);

		log.info("验证码 -------- {}", verCode);
		return Result.success(MapUtil.builder().put("uuid", key)
				.put("imgBase64", specCaptcha.toBase64())
				.build()
		);
	}


	@PostMapping("/register")
	public Result register(@Validated @RequestBody RegisterDto dto) {
		String realCode = LocalCache.get(dto.getUuid());
		if (realCode == null || !realCode.equals(dto.getCode().toLowerCase())) {
			return Result.fail("验证码不正确");
		}

		LocalCache.del(dto.getUuid());

		userService.register(dto);
		return Result.success();
	}
}
