package com.example.test2.controllers;

import com.example.test2.entities.BusinessCardEntity;
import com.example.test2.services.MainService;
import com.example.test2.vos.BusinessCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView getList(ModelAndView modelAndView) {
        return getListPage(modelAndView, 1);
    }

    @RequestMapping(value = "list/{page}", method = RequestMethod.GET)
    public ModelAndView getListPage(ModelAndView modelAndView,
                                    @PathVariable(name = "page", required = false) int page) {
        BusinessCardVo businessCardVo = this.mainService.getABusinessCard(1, page);
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
    public String getAdd(ModelAndView modelAndView,
                         String companyName,
                         String name,
                         String position,
                         String phone,
                         String email,
                         String addressPrimary) {
        BusinessCardEntity businessCardEntity = new BusinessCardEntity();
        businessCardEntity.setCompanyName(companyName);
        businessCardEntity.setName(name);
        businessCardEntity.setPosition(position);
        businessCardEntity.setPhone(phone);
        businessCardEntity.setEmail(email);
        businessCardEntity.setUserId(1);
        businessCardEntity.setAddress(addressPrimary);
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
    public String postModify(int index,
                             String companyName,
                             String name,
                             String position,
                             String phone,
                             String email,
                             String addressPrimary,
                             String request) {
        System.out.println(request);
        BusinessCardEntity businessCardEntity = new BusinessCardEntity();
        businessCardEntity.setIndex(index);
        businessCardEntity.setCompanyName(companyName);
        businessCardEntity.setName(name);
        businessCardEntity.setPosition(position);
        businessCardEntity.setPhone(phone);
        businessCardEntity.setEmail(email);
        businessCardEntity.setAddress(addressPrimary);
        this.mainService.setUpdateInfo(businessCardEntity);
        return "redirect:" + request;
    }
}
