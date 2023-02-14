package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 확인
		if(!(handler instanceof HandlerMethod)) {
			// DefaultServletHanlder가 처리하는 경우(정적 자원, /assets/**)
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Hanlder Method의 @Auth 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Handler Method에 @Auth가 없으면 Type(Class)에 붙어 있는 지 확인한다.
		
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		if(auth==null) {
			return true;
		}
		
		//5. Type이나 Method에 @Auth 가 없는 경우
		
		
		//6. @Auth가 붙어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String userId = request.getRequestURI().split("/")[3];
		if(authUser == null || !authUser.getId().equals(userId)) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		/*
		//7. 권한(Authorization) 체크를 위해 @Auth의 role 가져오기("ADMIN", "USER")
		String role = auth.role();
		if(role.equals("USER")) {
			return true;
		}
		
		if(role.equals("ADMIN")) {
			if(!authUser.getRole().equals(role)) {
				response.sendRedirect(request.getContextPath());
				return false;
			}
		}*/
		
		return true;
	}

}