package com.douzone.jblog.vo;

public class CategoryVo {
	private long no;
	private String name;
	private String blog_id;
	private String description;
	private String reg_date;
	private long postCount;
	
	public long getPostCount() {
		return postCount;
	}
	public void setPostCount(long postCount) {
		this.postCount = postCount;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", blog_id=" + blog_id + ", description=" + description
				+ ", reg_date=" + reg_date + ", postCount=" + postCount + "]";
	}
	
}
