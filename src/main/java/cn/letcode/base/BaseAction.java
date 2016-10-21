package cn.letcode.base;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import cn.letcode.utils.DataFormatUtils;

public class BaseAction {

	protected final static Logger log = LoggerFactory.getLogger(BaseAction.class);
	@Autowired
	protected ApplicationContext context;

	/**
	 * 
	 * @param response
	 * @param transBean
	 */
	protected void returnClient(ServletResponse response, TransBean transBean) {

		if (transBean.getLocalMap().containsKey("ret_code")) {
			transBean.addResponse("ret_code", transBean.getLocalParam("ret_code"));
			transBean.addResponse("ret_msg", transBean.getLocalParam("ret_msg"));
		} else {
			transBean.addResponse("ret_code", "0000");
			transBean.addResponse("ret_msg", "设置成功");
		}

		String retData =  DataFormatUtils.map2Json(transBean.getResponse());
		log.info("retrunClient:[{}]", retData);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(retData.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e1) {
			}
		}
	}
}
