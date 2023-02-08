package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getBlogById(String id) {
		return blogRepository.getBlogById(id);
	}

	public void createBlog(UserVo vo) {
		BlogVo blogvo = new BlogVo();
		blogvo.setBlog_id(vo.getId());
		blogvo.setTitle(vo.getName() + "의 블로그");
		blogvo.setLogo("/assets/images/spring-logo.jpg");
		blogRepository.createBlog(blogvo);
	}

	public void updateBlog(BlogVo vo) {
		blogRepository.updateBlog(vo);
	}

}
