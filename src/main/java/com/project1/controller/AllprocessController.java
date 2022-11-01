package com.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * 全流程信息控制器
 *
 */
@RequestMapping("/allprocess")
@Controller
public class AllprocessController {
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("allprocesses/allinfoManage");
        return model;
    }

}
