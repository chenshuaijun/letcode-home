package com.letcode.busi.services.note;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.letcode.base.TransBean;
import com.letcode.busi.services.BaseService;
import com.letcode.utils.CalendarUtil;

@Component
public class NoteService extends BaseService {
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void doService(TransBean transBean) throws SQLException {
		String sernoSql = "select nextval('note') serno from dual";
		SqlRowSet srs = jdbcTemplate.queryForRowSet(sernoSql);
		String serno = "";
		if (srs.next())
			serno = new StringBuffer(CalendarUtil.getCurrentDateTime())
					.append(StringUtils.leftPad(srs.getString("serno"), 7, "0")).toString();
		else
			return;
		String insert = "insert into note (serno,create_date,create_time,content,remark) values (?,?,?,?,?)";
		PreparedStatement pst = jdbcTemplate.getDataSource().getConnection().prepareStatement(insert);
		String content = (String) transBean.getRequestParam("content");
		content=Base64.encodeBase64String(content.getBytes());
		pst.setString(1, serno);
		pst.setString(2, CalendarUtil.getCurrentDateTime("yyyyMMdd"));
		pst.setString(3, CalendarUtil.getCurrentDateTime("HHmmss"));
		pst.setAsciiStream(4, new ByteArrayInputStream(content.getBytes()), content.getBytes().length);
		pst.setString(5, "");
		pst.executeUpdate();
	}
}
