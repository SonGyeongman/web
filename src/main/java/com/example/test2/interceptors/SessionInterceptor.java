package com.example.test2.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("userVo") != null){
            return true;
        }
        response.sendRedirect("/user/login");
        return false;
    }
}
