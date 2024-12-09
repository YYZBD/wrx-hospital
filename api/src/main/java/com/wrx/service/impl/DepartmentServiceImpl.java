package com.wrx.service.impl;

import com.wrx.entity.Department;
import com.wrx.mapper.DepartmentMapper;
import com.wrx.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 科室表 服务实现类
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    /**
     * 获取科室树
     * @return 科室信息及该科室所属的子科室
     */
    @Override
    public List<Department> getTree() {

        // 获取科室信构建页面树状结构
        return buildTree(this.list());

    }

    /**
     * 获取科室信构建页面树状结构
     * @param departments 顶部科室信息
     * @return 返回该科室所属的子科室
     */
    private List<Department> buildTree(List<Department> departments) {

        // 创建集合，准备将所有的科室放到集合中
        List<Department> result = new ArrayList<>();

        // 开始循环遍历所有的科室
        for(Department department : departments){

            // 再循环遍历出该科室下所属的子科室
            for(Department temp: departments){

                // 判断是否是该科室的子科室
                if(temp.getParentId()==department.getId().longValue()){

                    // 将子科室放到父科室的children集合中
                    department.getChildren().add(temp);

                }

            }

            // 获取出所有科室列表
            if(department.getParentId()==0){

                // 将父科室放到集合中
                result.add(department);

            }

        }
        return result;
    }
}
