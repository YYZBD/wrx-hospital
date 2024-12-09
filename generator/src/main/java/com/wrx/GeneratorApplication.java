package com.wrx;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class GeneratorApplication {

	public static void main(String[] args) {

		String basePath = System.getProperty("user.dir");

		// 为了测试，为了不直接生成到项目中
		basePath += "/aaa";

		// 输出路径
		String javaPath = basePath + "/api/src/main/java";
		String xmlPath = basePath + "/api/src/main/resources/mapper";
		String vuePath = basePath + "/front/src/views/admin";
		String apiPath = basePath + "/front/src/api/admin";

		FastAutoGenerator.create("jdbc:mysql://192.168.114.114:3306/hospital", "root", "123456")
				.templateEngine(new VelocityTemplateEngine())
				.globalConfig(builder -> {
					builder.author("一只会喵的布偶猫")
							.disableOpenDir()
							.outputDir(javaPath); // 指定代码输出路径
				})
				.packageConfig(builder -> {
					builder.parent("com.markerhub")
							.controller("controller.admin")
							.pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath));
				})
				.strategyConfig(builder -> {
					builder.addTablePrefix("sys_", "app_")
							.addInclude("app_booking","app_department","app_doctor","app_medical_card","app_medical_record","app_schedule")

							.controllerBuilder().template("/template/admin-controller.java.vm")
							.formatFileName("Admin%sController")

							.entityBuilder().enableLombok()
							.serviceBuilder().formatServiceFileName("%sService");
				})
				.injectionConfig(builder -> {

					// index.vue、 edit.vue  api.js
					builder
							.customFile(new CustomFile.Builder()
									.filePath(vuePath)
									.templatePath("/template/index.vue.vm")
									.fileName("index.vue")
									.formatNameFunction(tableInfo -> tableInfo.getEntityPath() + "/") //结合起来就是：用户管理 /user/index.vue
									.build())

							.customFile(new CustomFile.Builder()
									.filePath(vuePath)
									.templatePath("/template/edit.vue.vm")
									.fileName("edit.vue")
									.formatNameFunction(tableInfo -> tableInfo.getEntityPath() + "/") //结合起来就是：用户管理 /user/index.vue
									.build())

							.customFile(new CustomFile.Builder()
									.filePath(apiPath)
									.templatePath("/template/api.js.vm")
									.fileName(".js")
									.formatNameFunction(TableInfo::getEntityPath) //结合起来就是：用户管理 /user -> /user.js
									.build());
				})
				.execute();
	}

}
