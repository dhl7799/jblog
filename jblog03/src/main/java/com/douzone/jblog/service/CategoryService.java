package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostService postService;
	

	public void createCategory(CategoryVo cv) {
		categoryRepository.createCategory(cv);
	}

	public List<CategoryVo> getCategoryById(String id) {
		List<CategoryVo> list = categoryRepository.getCategoryById(id);
		for(CategoryVo vo : list) {
			vo.setPostCount(postService.countByCategoryNo(vo.getNo()));
		}
		
		return list;
	}

	public long getLastestCategoryNo() {
		return categoryRepository.getLastestCategoryNo();
	}

	public void deleteByNo(long no) {
		categoryRepository.deleteByNo(no);
	}
}
