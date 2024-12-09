package com.wrx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wrx.common.dto.LoginDto;
import com.wrx.common.dto.RegisterDto;
import com.wrx.common.dto.UserDto;
import com.wrx.entity.User;

/**
 * @author Administrator
 * @description 针对表【app_user(用户表)】的数据库操作Service
 * @createDate 2024-06-04 22:02:50
 */
public interface UserService extends IService<User> {

	UserDto login(LoginDto dto);

	boolean register(RegisterDto dto);

	UserDto updateInfo(User user);
}
