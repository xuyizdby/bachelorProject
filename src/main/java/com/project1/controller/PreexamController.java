package com.project1.controller;

import com.project1.model.Preexamer;
import com.project1.model.User;
import com.project1.page.Page;
import com.project1.service.IUserService;
import com.project1.service.PreexamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

/**
 * 提前面试控制器
 *
 */
@RequestMapping("/preexam")
@Controller
public class PreexamController {
    @Autowired
    private PreexamService preexamService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("preexam/preexamManage");
        return model;
    }
    @RequestMapping(value = "/get_list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "identifyID", required = false, defaultValue = "") String identifyID, @RequestParam(value = "companyIndustry", required = false, defaultValue = "") String companyIndustry, @RequestParam(value = "preexamStatus", required = false, defaultValue = "") String preexamStatus,@RequestParam(value = "preexamGrade", required = false, defaultValue = "") String preexamGrade,@RequestParam(value = "sex", required = false, defaultValue = "") String sex,Page page) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name", "%" + name + "%");
        queryMap.put("identifyID", "%" + identifyID + "%");
        queryMap.put("companyIndustry", companyIndustry);
        queryMap.put("preexamStatus", preexamStatus);
        queryMap.put("preexamGrade", preexamGrade);
        queryMap.put("sex", sex);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getPageSize());
        System.out.println(queryMap);
        ret.put("code", 0);
        ret.put("msg", "");
        ret.put("count", preexamService.getTotal(queryMap));
        System.out.println(ret);
        System.out.println("ahahhahahahhahahahhhhaahhahahahahhahahahha");
        ret.put("data", preexamService.findList(queryMap));
        System.out.println(ret);
        return ret;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(ModelAndView model) {
        model.setViewName("preexam/preexamAddForm");
        return model;
    }
//
@RequestMapping(value = "/show",method = RequestMethod.GET)
public ModelAndView show(ModelAndView model){
    model.setViewName("preexam/preexamForm");
    return model;
}
    @RequestMapping(value = "/read")
    @ResponseBody
    public Map<String, Object> read(@RequestParam("id") Long id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("id",id);
        System.out.println(queryMap);
//        ret.put("code", 0);
//        ret.put("msg", "");
//        ret.put("count", preexamService.getTotal(queryMap));
//        System.out.println(ret);
        System.out.println("ahahhahahahhahahahhhhaahhahahahahhahahahha");
        ret.put("data", preexamService.findDetail(queryMap));
        System.out.println(ret);
        return ret;
    }

    //test
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//        转换为json格式
    @ResponseBody
    public Map<String, String> add(Preexamer preexamer) {
        Map<String, String> ret = new HashMap<String, String>();
        if (preexamer == null) {
            ret.put("type", "error");
            ret.put("msg", "数据绑定出错");
            System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
            return ret;
        }
        //判断是否已存在该考生
        Preexamer existUser = preexamService.findPreexaminfo(preexamer.getIdentifyID());
        System.out.println(existUser);
        if (existUser != null) {
            ret.put("type", "error");
            ret.put("msg", "考生已存在");
            System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
            return ret;
        }
        if (preexamService.add(preexamer) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "添加失败");
            System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
            return ret;
        }
        System.out.println(preexamer.getName());
        ret.put("type", "success");
        ret.put("msg", "数据添加成功");
        System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈对了");
        return ret;
    }
//
//    /**
//     * 修改用户
//     * @return
//     */
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
////        转换为json格式
//    @ResponseBody
//    public Map<String, String> update(Preexamer preexamer) {
//        Map<String, String> ret = new HashMap<String, String>();
//        if (preexamer == null) {
//            ret.put("type", "error");
//            ret.put("msg", "数据绑定出错");
//            System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
//            return ret;
//        }
//        //判断是否已存在该用户
//        String existUser = preexamService.findUserinfo(preexamer.getIdentifyID());
////        System.out.println(user.getId().toString());
////        System.out.println(existUser);
////        System.out.println(user.getId().toString().equals(existUser));
//        if (existUser != null) {
//            if (preexamer.getId().toString().equals(existUser) == false) {
//                ret.put("type", "error");
//                ret.put("msg", "用户已存在");
//                System.out.println("错了");
//                return ret;
//            }
//        }
//        if (preexamService.update(preexamer) <= 0) {
//            ret.put("type", "error");
//            ret.put("msg", "修改失败");
//            System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
//            return ret;
//        }
//        System.out.println(preexamer.getIdentifyID());
//        ret.put("type", "success");
//        ret.put("msg", "数据添加成功");
//        System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈对了");
//        return ret;
//    }
//
    /**
     *
     */
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOne(@RequestParam("id") Long id) {
        System.out.println(id);
        Map<String, Object> map = new HashMap();
        preexamService.deleteOne(id);
        map.put("type", "success");
        return map;
    }

    @RequestMapping(value = "/deleteMany", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteMany(
            @RequestParam(value = "ids[]", required = true) Long[] ids
    ) {
        Map<String, String> ret = new HashMap<String, String>();
        if (ids == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择数据!");
            return ret;
        }
        String idsString = "";
        for (Long id : ids) {
            idsString += id + ",";
        }
        idsString = idsString.substring(0, idsString.length() - 1);
        if (preexamService.deleteMany(idsString) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "删除失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;
    }
//    可视化界面
@RequestMapping(value = "/statistics",method = RequestMethod.GET)
public ModelAndView statistics(ModelAndView model){
    model.setViewName("preexam/preexamStatistics");
    return model;
}

@RequestMapping(value = "/sexStats",method = RequestMethod.GET)
@ResponseBody
public List sexStats(){
    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    List<Map> list2=preexamService.sexStats();
    for(Map m:list2){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("value",m.get("peoNum").toString());
        map.put("name",m.get("sex").toString());
        list.add(map);
    }
    System.out.println(list);
    return list;
}
@RequestMapping(value = "/industryStats",method = RequestMethod.GET)
@ResponseBody
public List industryStats(){ List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
List<Map> list2=preexamService.industryStats();
for(Map m:list2){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("value",m.get("peoNum").toString());
    map.put("name",m.get("companyIndustry").toString());
    list.add(map);
}
System.out.println(list);
return list; }

    @RequestMapping(value = "/incomeStats",method = RequestMethod.GET)
    @ResponseBody
    public List incomeStats(){ List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Map> list2=preexamService.industryStats();
        for(Map m:list2){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("value",m.get("peoNum").toString());
            map.put("name",m.get("annualIncome").toString());
            list.add(map);
        }
        System.out.println(list);
        return list; }

}


