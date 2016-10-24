package cn.letcode.busi.services.bbs.grouptopic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.letcode.db.ComnDao;

@Repository
public class GroupTopicService {
	private String	PREFIX	= this.getClass().getName() + ".";
	@Autowired
	private ComnDao	comnDao;

	public List<?> queryTopicList() {
		return comnDao.getList(PREFIX, "queryGroupTopicList");
	}

}
