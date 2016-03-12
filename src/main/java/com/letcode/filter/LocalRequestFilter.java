package com.letcode.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.letcode.base.RequestSupport;

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
		chain.doFilter(request, response);
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
}
