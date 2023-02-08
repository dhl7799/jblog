package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlsession; 
	
	public void join(UserVo vo) {
		sqlsession.insert("user.join", vo);
	}

	public long getNo() {
		return sqlsession.selectOne("user.getNo");
	}

	public UserVo getUserByIdAndPassword(String id, String password) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		return sqlsession.selectOne("user.getUserByIdAndPassword",map);
	}

}
