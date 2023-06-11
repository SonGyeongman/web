package com.example.test2.controllers;

import com.example.test2.entities.BusinessCardEntity;
import com.example.test2.services.MainService;
import com.example.test2.vos.BusinessCardVo;
import com.example.test2.vos.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView getList(ModelAndView modelAndView,
                                HttpServletRequest request,
                                HttpSession session) {
        return getListPage(modelAndView, session, request, 1);
    }

    @RequestMapping(value = "list/{page}", method = RequestMethod.GET)
    public ModelAndView getListPage(ModelAndView modelAndView,
                                    HttpSession session,
                                    HttpServletRequest request,
                                    @PathVariable(name = "page", required = false) int page) {
        BusinessCardVo businessCardVo = null;
        UserVo userVo = this.getSession(session, request);
        businessCardVo = this.mainService.getMainList(userVo.getIndex(), page);
        businessCardVo.setCheck("list");
        modelAndView.addObject("businessCardVos", businessCardVo)
                .setViewName("main/index");
        return modelAndView;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView getSearch(ModelAndView modelAndView,
                                  String searchSelect,
                                  String search,
                                  int page,
                                  HttpServletRequest request,
                                  HttpSession session) {
        if (searchSelect.equals("all")) {
            modelAndView.setViewName("redirect:/main/list");
            return modelAndView;
        }
        BusinessCardVo businessCardVo = null;
        UserVo userVo = this.getSession(session, request);
        businessCardVo = this.mainService.getSearchMainList(userVo.getIndex(), page, searchSelect, search);
        businessCardVo.setSearchSelect(searchSelect);
        businessCardVo.setSearch(search);
        businessCardVo.setCheck("search");
        modelAndView.addObject("businessCardVos", businessCardVo)
                .setViewName("main/index");
        return modelAndView;
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String getListRemove(HttpServletRequest request,
                                int index) {
        this.mainService.deleteBusinessCard(index);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView getAdd(ModelAndView modelAndView) {
        modelAndView.setViewName("main/add");
        return modelAndView;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String getAdd(BusinessCardEntity businessCardEntity,
                         HttpServletRequest request,
                         HttpSession session) {
        UserVo userVo = this.getSession(session, request);
        businessCardEntity.setUserId(userVo.getIndex());
        this.mainService.setBusinessCard(businessCardEntity);
        return "redirect:/main/list";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public ModelAndView getModify(ModelAndView modelAndView,
                                  HttpServletRequest request,
                                  int index) {
        String referer = request.getHeader("Referer");
        BusinessCardEntity businessCardEntity = this.mainService.getUpdateInfo(index);
        modelAndView.addObject("referer", referer)
                .addObject("businessCardEntity", businessCardEntity)
                .setViewName("main/modify");
        return modelAndView;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String postModify(BusinessCardEntity businessCardEntity,
                             HttpSession session,
                             HttpServletRequest request,
                             String referer) {
        UserVo userVo = this.getSession(session, request);
        businessCardEntity.setUserId(userVo.getIndex());
        this.mainService.setUpdateInfo(businessCardEntity);
        return "redirect:" + referer;
    }

    private UserVo getSession(HttpSession session,
                              HttpServletRequest request) {
        session = request.getSession();
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        return userVo;
    }
}
