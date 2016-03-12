package com.letcode.busi.services.ball;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.letcode.base.TransBean;
import com.letcode.busi.services.BaseService;

@Component
public class BallService extends BaseService{
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void doService(TransBean transBean) throws SQLException {
		String sql = "select date_no, sale_count, sale_amt, red_ball1, red_ball2, red_ball3, red_ball4, red_ball5, red_ball6, blue_ball1 from shuangseball ssb order by date_no desc limit 1 ";
		List list = jdbcTemplate.queryForList(sql);
		if(!list.isEmpty()){
			transBean.addResponse("result", list.get(0));
		}
	}
}
