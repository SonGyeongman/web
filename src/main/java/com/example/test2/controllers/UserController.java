package com.example.test2.controllers;

import com.example.test2.enums.UserEnum;
import com.example.test2.services.UserService;
import com.example.test2.vos.UserVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView getLogin(ModelAndView modelAndView) {
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView postLogin(ModelAndView modelAndView,
                                  HttpServletResponse response,
                                  HttpServletRequest request,
                                  HttpSession session,
                                  UserVo userVo){
        userVo.setResult(null);
        this.userService.login(userVo);
//        if(userVo.getResult() == UserEnum.SUCCESS){
//            Cookie cookie = new Cookie("sk", String.valueOf(userVo.getIndex()));
//            cookie.setPath("/");
//            response.addCookie(cookie);
//        }
        if(userVo.getResult() == UserEnum.SUCCESS){
            session = request.getSession();
            session.setAttribute("userVo", userVo);
        }
        modelAndView.addObject("userVo", userVo)
                .setViewName("user/login");
        return modelAndView;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView getLogout(HttpServletRequest request,
                                  HttpServletResponse response,
                                  HttpSession session,
                                  ModelAndView modelAndView){
//        if(request.getCookies() != null){
//            for(Cookie cookie : request.getCookies()){
//                if(cookie.getName().equals("sk")){
//                    cookie.setMaxAge(0);
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                    break;
//                }
//            }
//        }
        session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        modelAndView.setViewName("redirect:/user/login");
        return modelAndView;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView getRegister(ModelAndView modelAndView){
        modelAndView.setViewName("user/register");
        return modelAndView;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView postRegister(ModelAndView modelAndView,
                                     UserVo userVo){
        this.userService.register(userVo);
        modelAndView.addObject("userVo", userVo)
                .setViewName("user/register");
        return modelAndView;
    }

    @RequestMapping(value = "email-check", method = RequestMethod.GET)
    @ResponseBody
    public String getEmailCheck(String email){
        return this.userService.emailCheck(email);
    }

    @RequestMapping(value = "phone-check", method = RequestMethod.GET)
    @ResponseBody
    public String getPhoneCheck(String phone){
        return this.userService.phoneCheck(phone);
    }
}
