<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/blog/includes/header.jsp"/>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${empty postList || postIndex eq -1 }">
						<h4>아무 글도 없습니다</h4>
						<p>
							
						<p>
						</c:when>
						<c:otherwise>
						<h4>${postList.get(postIndex).title }</h4>
						<p>
							${postList.get(postIndex).content }
						<p>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${authUser.id eq vo.blog_id && postList.size() ne 0}">
							<a href="${pageContext.request.contextPath}/blog/${vo.blog_id }/${postList.get(postIndex).category_no }/${postList.get(postIndex).no }/delete">삭제</a>
						</c:when>
					</c:choose>
				</div>
				<ul class="blog-list">
				<c:forEach items="${postList }" var="postList" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/blog/${vo.blog_id }/${postList.category_no }/${postList.no }">${postList.title }</a> <span>${postList.reg_date }</span></li>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${vo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<c:forEach items="${categoryList }" var="categoryList" varStatus="status">
			<ul>
				<li><a href="${pageContext.request.contextPath}/blog/${vo.blog_id }/${categoryList.no }">${categoryList.name }</a></li>
			</ul>
			</c:forEach>
		</div>

		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2023
			</p>
		</div>
	</div>
</body>
</html>