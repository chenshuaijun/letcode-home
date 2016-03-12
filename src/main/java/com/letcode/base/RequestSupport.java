package com.letcode.base;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.letcode.utils.JsonUtil;

public class RequestSupport {
	private static Logger							log				= LoggerFactory.getLogger(RequestSupport.class);

	/**
	 * 标志不打印日志的参数变量名称
	 */
	public final static String						DO_NOT_SAY_LOG	= "sys_donotsaylog";

	/**
	 * 用于保存访问的请求对象
	 */
	private static ThreadLocal<HttpServletRequest>	tlLocalRequest	= new ThreadLocal<HttpServletRequest>();

	private static ThreadLocal<Map>					allparams		= new ThreadLocal<Map>();

	/**
	 * 保存当前请求对象
	 */
	public static void setLocalRequest(HttpServletRequest request) {
		tlLocalRequest.set(request);
	}

	/**
	 * 获取当前请求对象
	 */
	public static HttpServletRequest getLocalRequest() {
		return tlLocalRequest.get();
	}

	/**
	 * 清除threadlocal中的参数
	 */
	public static void clearUserParams() {
		if (allparams.get() != null) {
			allparams.set(null);
		}
	}

	/**
	 * 手动设置流程中的参数
	 *
	 * @param map
	 */
	public static void setUserParameters(Map map) {
		Map p = allparams.get();
		if (p == null) {
			p = new HashMap();
			allparams.set(p);
		}
		if (map == null) {
			p = new HashMap();
			allparams.set(p);
			return;
		}

		allparams.get().putAll(map);
	}

	/**
	 * 取得当前请求的参数集
	 *
	 * @return
	 */
	public static Map<String, Object> getParameters() {

		try {
			if (allparams.get() == null) {
				setRequestParams();
			}

			return allparams.get();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 设置请求参数
	 */
	public static void setRequestParams() {
		try {
			// 初始化allparams
			setUserParameters(null);
			Map<String, Object> map = null;
			String contentType = tlLocalRequest.get().getContentType();

			if (contentType != null && contentType.contains("application/json")) {
				map = getJsonParameters();
				setUserParameters(map);
			} else {
				HttpServletRequest r = (HttpServletRequest) getLocalRequest();
				Map params = r.getParameterMap();
				if (params == null || params.size() <= 0) {
					return;
				}
				setUserParameters(map);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 获取当前请求的参数值<br />
	 * 供bizr2.tld标签库使用的方法
	 *
	 * @return
	 */
	public static String getRequestParamValue(String paraname) {
		Object obj = getParameters().get(paraname);
		if (obj == null)
			return "";
		return obj.toString();
	}

	@SuppressWarnings("all")
	private static Map<String, Object> getJsonParameters() {
		try {
			HttpServletRequest r = (HttpServletRequest) getLocalRequest();
			BufferedReader reader = r.getReader();
			StringBuffer buffer = new StringBuffer();
			String str;
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}
			reader.close();
			Map<String, Object> map = new HashMap();
			map.putAll((Map<String, Object>) JsonUtil.jsonToMap(buffer.toString()));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
