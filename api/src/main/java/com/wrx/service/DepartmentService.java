package com.wrx.service;

import com.wrx.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 科室表 服务类
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
public interface DepartmentService extends IService<Department> {

    List<Department> getTree();
}
