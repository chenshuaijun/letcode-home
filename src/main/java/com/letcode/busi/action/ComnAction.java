package com.letcode.busi.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.letcode.base.BaseAction;
import com.letcode.base.RequestSupport;
import com.letcode.base.TransBean;
import com.letcode.busi.services.BaseService;
import com.letcode.busi.services.shuangseball.ShuangSeBallService;

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

	@Autowired
	public ShuangSeBallService shuangseballService;

	@RequestMapping(value = "/shuangseball", method = RequestMethod.POST)
	public void shuangseball(String tranType, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws UnsupportedEncodingException, IOException {
		TransBean transBean = new TransBean();
		transBean.addResponse("result",(Map<String, Object>) shuangseballService.queryShuangSeBall(null));
		returnClient(response, transBean);
	}
}
