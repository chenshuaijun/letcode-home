package cn.letcode.busi.services;

import java.sql.SQLException;

import cn.letcode.base.TransBean;

public abstract class BaseService {
	public abstract void doService(TransBean transBean) throws SQLException;
}
