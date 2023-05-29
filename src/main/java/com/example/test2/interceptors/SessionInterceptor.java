package com.example.test2.interceptors;

import com.example.test2.entities.UserEntity;
import com.example.test2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("sk")){
                    UserEntity userEntity = this.userService.sessionUser(Integer.valueOf(cookie.getValue()));
                    request.setAttribute("sessionEntity", userEntity);
                    return true;
                }
            }
        }
        response.sendRedirect("/user/login");
        return false;
    }
}
