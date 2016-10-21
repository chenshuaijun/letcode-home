package cn.letcode.base;

import java.util.HashMap;
import java.util.Map;

import cn.letcode.exception.ErrorException;


public class TransBean {

	private Map<String, Object> request;
	private Map<String, Object> response;
	private Map<String, Object> localMap;
	private Map<String, Object> transCodeMap;

	private ErrorException errorException = null;
	
	
	
	/**
	 * 添加返回參數
	 * 
	 * @param key  參數名
	 * @param value  參數值
	 */
	public void addResponse(String key, Object value) {
		if (response == null) {
			response = new HashMap<String, Object>();
		}
		response.put(key, value);
	}
	/**
	 * 添加本地參數
	 * 
	 * @param key  參數名
	 * @param value  參數值
	 */
	public void addLocalParam(String key, Object value) {
		if (localMap == null) {
			localMap = new HashMap<String, Object>();
		}
		localMap.put(key, value);
	}


	/**
	 * 返回本地参数
	 * @param key
	 * @return
	 */
	public Object getLocalParam(String key){
		if(this.localMap==null){
			return null;
		}
		Object value=localMap.get(key);
		
		return value;
	}
	/**
	 * 返回请求参数
	 * @param key
	 * @return
	 */
	public Object getRequestParam(String key){
		if(this.request==null){
			return null;
		}
		Object value=request.get(key);
		
		return value;
	}
	
	
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getResponse() {
		return response;
	}

	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}

	public Map<String, Object> getLocalMap() {
		if(localMap==null){
			localMap=new HashMap<String,Object>();
		}
		return localMap;
	}

	public void setLocalMap(Map<String, Object> localMap) {
		this.localMap = localMap;
	}

	public Map<String, Object> getTransCodeMap() {
		return transCodeMap;
	}

	public void setTransCodeMap(Map<String, Object> transCodeMap) {
		this.transCodeMap = transCodeMap;
	}
	public ErrorException getErrorException() {
		return errorException;
	}
	public void setErrorException(ErrorException errorException) {
		this.errorException = errorException;
	}

}
