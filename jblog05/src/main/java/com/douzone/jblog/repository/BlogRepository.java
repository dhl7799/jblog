package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlogById(String id) {
		return sqlSession.selectOne("blog.getBlogById", id);
	}

	public void createBlog(BlogVo vo) {
		sqlSession.insert("blog.createBlog", vo);
	}

	public void updateBlog(BlogVo vo) {
		sqlSession.update("blog.updateBlog",vo);
	}

}
