package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public void post(PostVo pv) {
		sqlSession.insert("post.post", pv);
	}

	public List<PostVo> getPostByCategoryNo(long categoryNo) {
		return sqlSession.selectList("post.getPostByCategoryNo", categoryNo);
	}

	public long countByCategoryNo(long no) {
		return sqlSession.selectOne("post.countByCategoryNo",no);
	}

	public void deleteByNo(long postNo) {
		sqlSession.delete("post.deleteByNo", postNo);
	}
	
}
