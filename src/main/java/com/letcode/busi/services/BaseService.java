package com.letcode.busi.services;

import java.sql.SQLException;

import com.letcode.base.TransBean;

public abstract class BaseService {
	public abstract void doService(TransBean transBean) throws SQLException;
}
