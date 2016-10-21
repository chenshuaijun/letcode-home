package cn.letcode.busi.services.shuangseball;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.letcode.db.ComnDao;

@Repository
public class ShuangSeBallService {
	private String	PREFIX	= this.getClass().getName() + ".";
	@Autowired
	private ComnDao	comnDao;

	/**
	 * 查询双色球单条记录
	 * 
	 * @param ball
	 * @return
	 */
	public Map<String, Object> queryShuangSeBall(ShuangSeBall ball) {
		return comnDao.get(PREFIX, "queryShuangSeBall", ball);
	}

}
