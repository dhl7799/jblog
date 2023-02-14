package com.douzone.jblog.vo;

public class BlogVo {
	private String blog_id;
	private String title;
	private String logo;
	
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "BlogVo [blog_id=" + blog_id + ", title=" + title + ", logo=" + logo + "]";
	}
	
}
