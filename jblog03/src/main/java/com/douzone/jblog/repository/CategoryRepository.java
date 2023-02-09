package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void createCategory(CategoryVo cv) {
		sqlSession.insert("category.createCategory",cv);
	}

	public List<CategoryVo> getCategoryById(String id) {
		return sqlSession.selectList("category.getCategoryById", id);
	}

	public long getLastestCategoryNo() {
		return sqlSession.selectOne("category.getLastestCategoryNo");
	}

	public void deleteByNo(long no) {
		sqlSession.delete("category.deleteByNo", no);
	}

	public long countCategoryNoById(String id) {
		return sqlSession.selectOne("category.countCategoryNoById", id);
	}
	
	
}
