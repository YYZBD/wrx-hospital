package com.wrx.common.lang;

import cn.hutool.core.map.MapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstDict {

	public static final Map<Integer, String> orderStatusDict = new HashMap<>();

	static {
		orderStatusDict.put(0, "待付款");
		orderStatusDict.put(1, "待发货");

		// ...
	}

	public static List<Map<Object, Object>> getMapList(Map<Integer, String> dict) {
		return dict.entrySet().stream().map(e -> MapUtil.builder().put("value", e.getKey()).put("label", e.getValue()).build()).toList();
	}

}
