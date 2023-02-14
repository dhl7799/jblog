package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/joinform")
	public String joinform() {
		return "/user/join";
	}
	
	@RequestMapping("/join")
	public String join(String name, String id, String password) {
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setId(id);
		vo.setPassword(password);
		userService.join(vo);
		blogService.createBlog(vo);
		
		CategoryVo cv = new CategoryVo();
		cv.setBlog_id(id);
		cv.setName("기본 카테고리");
		cv.setDescription("기본 카테고리 입니다");
		categoryService.createCategory(cv);
		
		/*
		PostVo pv = new PostVo();
		long categoryNo = categoryService.getLastestCategoryNo();
		pv.setCategory_no(categoryNo);
		pv.setTitle("안녕하세요 " + vo.getName() +" 입니다.");
		pv.setContent("첫 포스트 입니다.");
		postService.post(pv);
		*/
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform() {
		return "/user/login";
	}
	
	@RequestMapping("/login")
	public void auth() {
	}
	
	@RequestMapping("/logout")
	public void logout() {
	}
	
	/*
	@RequestMapping("/login")
	public String login(String id, String password) {
		UserVo vo = userService.getUserByIdAndPassword(id,password);
		if(vo == null) {
			System.out.println("login failed");
		}
		System.out.println("login success. welcome " + vo.getName());
		return "redirect:/main";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/main";
	}*/
}
