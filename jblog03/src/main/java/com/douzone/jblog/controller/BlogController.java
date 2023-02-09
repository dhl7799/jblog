package com.douzone.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;
import com.douzone.jblog.service.FileUploadService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlogById(id);
		servletContext.setAttribute("vo",vo);
		List<CategoryVo> list = categoryService.getCategoryById(id);
		servletContext.setAttribute("categoryList",list);
		long categoryNo = list.get(0).getNo();
		List<PostVo> plist = postService.getPostByCategoryNo(categoryNo);
		servletContext.setAttribute("postList",plist);

		if(plist.size()>0) {
			model.addAttribute("postIndex",0);
		}
		else {
			model.addAttribute("postIndex",-1);
		}
		
		model.addAttribute("blog_admin_id",id);
		return "/blog/blog-main";
	}
	
	@RequestMapping("/{id}/{categoryNo}/{postNo}")
	public String read(@PathVariable("id") String id, @PathVariable("postNo") long postNo, 
			@PathVariable("categoryNo") long categoryNo, Model model) {
		BlogVo vo = blogService.getBlogById(id);
		servletContext.setAttribute("vo",vo);
		List<CategoryVo> list = categoryService.getCategoryById(id);
		servletContext.setAttribute("categoryList",list);
		List<PostVo> plist = postService.getPostByCategoryNo(categoryNo);
		servletContext.setAttribute("postList",plist);
		long postIndex = 0l;
		for(PostVo pv:plist) {
			if(pv.getNo()==postNo)
				postIndex = plist.indexOf(pv);
		}
		model.addAttribute("postIndex",postIndex);
		return "/blog/blog-main";
	}
	
	@RequestMapping("/{id}/{categoryNo}/{postNo}/delete")
	public String deletePost(@PathVariable("id") String id, @PathVariable("postNo") long postNo, 
			@PathVariable("categoryNo") long categoryNo, Model model, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/blog/"+id;
		}
		postService.deleteByNo(postNo);
		
		return "redirect:/blog/"+id+"/"+categoryNo;
	}
	
	@RequestMapping("/{id}/{categoryNo}")
	public String searchByCategory(@PathVariable("id") String id, @PathVariable("categoryNo") long categoryNo) {
		List<CategoryVo> list = categoryService.getCategoryById(id);
		servletContext.setAttribute("categoryList",list);
		List<PostVo> plist = postService.getPostByCategoryNo(categoryNo);
		servletContext.setAttribute("postList",plist);
		return "/blog/blog-main";
	}
	
	
	@RequestMapping("/{id}/admin")
	public String adminPage(@PathVariable("id") String id, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/blog/"+id;
		}
		
		return "/blog/blog-admin-basic";
	}
	
	@RequestMapping("/{id}/admin/category")
	public String category(@PathVariable("id") String id, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/blog/"+id;
		}
		
		return "/blog/blog-admin-category";
	}
	
	@RequestMapping("/{id}/admin/category/add")
	public String addCategory(@PathVariable("id") String id, String name, String desc, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/blog/"+id;
		}
		
		
		CategoryVo vo = new CategoryVo();
		vo.setBlog_id(id);
		vo.setName(name);
		vo.setDescription(desc);
		categoryService.createCategory(vo);
		List<CategoryVo> list = categoryService.getCategoryById(id);
		servletContext.setAttribute("categoryList",list);
		return "redirect:/blog/"+id+"/admin/category";
	}
	
	@RequestMapping("/{id}/admin/category/delete")
	public String deleteCategory(@PathVariable("id") String id, long no, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/blog/"+id;
		}
		
		
		long postCount = postService.countByCategoryNo(no);
		long categoryCount = categoryService.countCategoryNoById(id);
		if(postCount > 0 || categoryCount==1) {
			return "redirect:/blog/"+id+"/admin/category";
		}
		categoryService.deleteByNo(no);
		List<CategoryVo> list = categoryService.getCategoryById(id);
		servletContext.setAttribute("categoryList",list);
		return "redirect:/blog/"+id+"/admin/category";
	}
	
	@RequestMapping("/{id}/admin/write")
	public String write(@PathVariable("id") String id, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/blog/"+id;
		}
		
		return "/blog/blog-admin-write";
	}
	
	@RequestMapping("/{id}/admin/write/post")
	public String post(@PathVariable("id") String id, String title, long category, String content, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/blog/"+id;
		}
		
		PostVo vo = new PostVo();
		vo.setTitle(title);
		vo.setCategory_no(category);
		vo.setContent(content);
		postService.post(vo);
		return "redirect:/blog/"+id;
	}
	
	@RequestMapping("/{id}/admin/update")
	public String update(@PathVariable("id") String id, 
			BlogVo vo,
			MultipartFile logo_file, HttpSession session) {
		vo.setBlog_id(id);
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !authUser.getId().equals(id)) {
			return "redirect:/blog/"+id;
		}
		
		String logo = fileuploadService.restore(logo_file);
		if(logo!=null) {
			vo.setLogo(logo);
		}

		blogService.updateBlog(vo);
		servletContext.setAttribute("vo",vo);
		return "redirect:/blog/"+id+"/admin";
	}
	
	
}
