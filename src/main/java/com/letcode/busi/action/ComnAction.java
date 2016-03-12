package com.letcode.busi.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.letcode.base.BaseAction;
import com.letcode.base.RequestSupport;
import com.letcode.base.TransBean;
import com.letcode.busi.services.BaseService;

@Controller
public class ComnAction extends BaseAction {

	@RequestMapping(value = "/{tranType}", method = RequestMethod.POST)
	public void comn(@PathVariable(value = "tranType") String tranType, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws UnsupportedEncodingException, IOException {
		BaseService service = (BaseService) context.getBean(tranType);
		TransBean transBean = new TransBean();
		transBean.setRequest(RequestSupport.getParameters());
		try {
			service.doService(transBean);
			returnClient(response, transBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
