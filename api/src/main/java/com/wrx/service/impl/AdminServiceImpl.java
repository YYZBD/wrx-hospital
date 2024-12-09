package com.wrx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wrx.entity.Admin;
import com.wrx.service.AdminService;
import com.wrx.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【sys_admin】的数据库操作Service实现
* @createDate 2024-06-18 11:01:38
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




