package com.markerhub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.markerhub.common.dto.LoginDto;
import com.markerhub.common.dto.RegisterDto;
import com.markerhub.common.dto.UserDto;
import com.markerhub.entity.User;

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
