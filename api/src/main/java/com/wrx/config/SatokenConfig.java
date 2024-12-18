package com.wrx.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.strategy.SaStrategy;
import com.wrx.security.StpKit;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SatokenConfig implements WebMvcConfigurer {

	@Value("${sa-token.jwt-secret-key}")
	private String jwtSecretKey;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册 Sa-Token 拦截器，打开注解式鉴权功能
		registry.addInterceptor(new SaInterceptor(handle -> StpKit.ADMIN.checkLogin()))
				.addPathPatterns("/sys/**", "/admin/**")
				.excludePathPatterns("/sys/login");

		registry.addInterceptor(new SaInterceptor()).addPathPatterns("/app/**");
	}

	@PostConstruct
	public void setSaTokenConfig() {
		SaTokenConfig adminConfig = new SaTokenConfig();
		adminConfig.setTokenName("adminToken");
		adminConfig.setIsReadCookie(false);
		adminConfig.setJwtSecretKey(jwtSecretKey);
		StpKit.ADMIN.setConfig(adminConfig);

		SaTokenConfig userConfig = new SaTokenConfig();
		userConfig.setTokenName("userToken");
		userConfig.setIsReadCookie(false);
		userConfig.setJwtSecretKey(jwtSecretKey);
		StpKit.USER.setConfig(userConfig);
	}

	@PostConstruct
	public void rewriteSaStrategy() {
		// 重写Sa-Token的注解处理器，增加注解合并功能
		SaStrategy.instance.getAnnotation = AnnotatedElementUtils::getMergedAnnotation;
	}
}
