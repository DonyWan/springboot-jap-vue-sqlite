package com.udbac.versionpublish.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.udbac.versionpublish.util.JwtUtil;

import io.jsonwebtoken.Claims;

public class TokenInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        // TODO Auto-generated method stub
    	String token = request.getHeader("token");
    	// 验证token
    	Claims claims = JwtUtil.parseJWT(token);
    	boolean b = claims==null || claims.isEmpty() || JwtUtil.isTokenExpired(claims.getExpiration());
    	if(b) {
    		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    		return false;
    	}
        return super.preHandle(request, response, handler);
    }
    @Override
    public void postHandle(HttpServletRequest request,
    		HttpServletResponse response, Object handler,
    		ModelAndView modelAndView) throws Exception {
    	// TODO Auto-generated method stub
    	super.postHandle(request, response, handler, modelAndView);
    }
    @Override
    public void afterCompletion(HttpServletRequest request,
    		HttpServletResponse response, Object handler, Exception ex)
    		throws Exception {
    	// TODO Auto-generated method stub
    	super.afterCompletion(request, response, handler, ex);
    }
}
