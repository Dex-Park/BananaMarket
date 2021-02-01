package com.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.banana.vo.SessionVO;

public class AuthHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		    throws Exception {
		
		//1. ���������� �����´�.
		HttpSession session = request.getSession();
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		
		//2. svo ��ü�� üũ. -> svo�� ������ �ƴ���.
		if(svo == null) {
			response.sendRedirect("/banana/login.do");
			return false;
		}
		
		return true;
	}
	
}
