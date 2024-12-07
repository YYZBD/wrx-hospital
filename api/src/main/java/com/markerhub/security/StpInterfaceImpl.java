package com.markerhub.security;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.ListUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

	@Override
	public List<String> getPermissionList(Object loginId, String loginType) {
		return ListUtil.empty();
	}

	@Override
	public List<String> getRoleList(Object loginId, String loginType) {
		return ListUtil.empty();
	}

}
