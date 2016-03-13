package com.letcode.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letcode.base.RequestSupport;

/**
 * 平台请求过滤器
 * 
 * @author chensj
 *
 */
public class LocalRequestFilter implements Filter {

	private String	encoding;

	private boolean	forceEncoding;
	private String	errorPage;

	public void init(FilterConfig config) throws ServletException {

		encoding = config.getInitParameter("encoding");
		encoding = encoding == null ? "UTF-8" : encoding;
		if ("true".equals(config.getInitParameter("forceEncoding"))) {
			forceEncoding = true;
		} else {
			forceEncoding = false;
		}
		errorPage = config.getInitParameter("error-page");
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
			if (forceEncoding) {
				response.setCharacterEncoding(encoding);
			}
		}
		RequestSupport.setLocalRequest((HttpServletRequest) request);
		RequestSupport.setRequestParams();
		// 解决访问跨域问题
		dealCorsAccess((HttpServletResponse) response);
		chain.doFilter(request, response);
	}

	/**
	 * 处理跨域访问问题
	 * 
	 * @param response
	 */
	public void dealCorsAccess(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
}
