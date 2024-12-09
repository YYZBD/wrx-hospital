package com.wrx.controller.app;


import com.wrx.common.lang.Result;
import com.wrx.service.IndexService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class IndexController {


    // 首页科室与医生内容封装
    @Resource
    IndexService indexService;

    /**
     * 首页
     * 科室与专家内容请求接口
     * @return
     */
    @GetMapping("/home")
    public Result home() {

        // 获取首页科室与医生内容数据
        return Result.success(indexService.getHome());
    }

}
