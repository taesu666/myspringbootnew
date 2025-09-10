package lx.edu.springboot.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lx.edu.springboot.vo.AddrBookVO;


@Component
public class AddrBookDAO {
	
	@Autowired
	SqlSession session;

	public int insertDB(AddrBookVO ab) throws Exception {
		return session.insert("insertDB", ab);
		
	}

	public List<AddrBookVO> getDBList() throws Exception {
		return session.selectList("getDBList");
	}

	public int updateDB(AddrBookVO ab) throws Exception {
		return session.update("updateDB", ab);
	}
	
	public AddrBookVO selectDBList(int abId) throws Exception {
		return session.selectOne("selectDBList", abId);
	}
	
}
