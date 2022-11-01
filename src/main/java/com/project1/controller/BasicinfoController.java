package com.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * 基础信息控制器
 *
 */
@RequestMapping("/basicInfo")
@Controller
public class BasicinfoController {
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("basicInfo/majorManage");
        return model;
    }

}

