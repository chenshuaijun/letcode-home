package com.letcode.utils;

import java.util.Map;

import org.json.JSONObject;

/**
 * 数据格式转换处理类
 * 
 * @author chensj
 *
 */
public class DataFormatUtils {
	/**
	 * 将map格式数据转成json 字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String map2Json(Map<?, ?> map) {
		JSONObject json = (JSONObject) JSONObject.wrap(map);
		return json.toString();
	}
}
