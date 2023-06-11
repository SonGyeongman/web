package com.example.test2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class RootController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getRoot(ModelAndView modelAndView){
        modelAndView.setViewName("redirect:/main/list");
        return modelAndView;
    }
}
