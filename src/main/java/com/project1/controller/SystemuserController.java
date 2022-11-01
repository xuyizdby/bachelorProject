package com.project1.controller;

import com.project1.model.User;
import com.project1.page.Page;
import com.project1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     * 用户管理控制器
     *
     */
    @RequestMapping("/user")
    @Controller
    public class SystemuserController {
        @Autowired
        private IUserService userService;

        @RequestMapping(value = "/list",method = RequestMethod.GET)
        public ModelAndView list(ModelAndView model){
            model.setViewName("systemuser/userManage");
            return model;
        }
        @RequestMapping(value = "/get_list",method = RequestMethod.POST)
        @ResponseBody
        public Map<String,Object> getList(@RequestParam(value="username",required = false,defaultValue = "")String username,@RequestParam(value="email",required = false,defaultValue = "")String email,@RequestParam(value="role",required = false,defaultValue = "")String role,Page page){
            System.out.println("ahahhahahahhahahahhhhaahhahahahahhahahahha");
            Map<String, Object> ret = new HashMap<String, Object>();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("username","%"+username+"%");
            queryMap.put("email","%"+email+"%");
            queryMap.put("role",role);
            queryMap.put("offset",page.getOffset());
            queryMap.put("pageSize",page.getPageSize());
            System.out.println(queryMap);
            ret.put("code",0);
            ret.put("msg","");
            ret.put("count",userService.getTotal(queryMap));
            System.out.println(userService.getTotal(queryMap));
            ret.put("data",userService.findList(queryMap));
            System.out.println(ret);
            return ret;
        }
        @RequestMapping(value = "/add",method = RequestMethod.GET)
        public ModelAndView add(ModelAndView model){
            model.setViewName("systemuser/userForm");
            return model;
        }
        @RequestMapping(value = "/edit",method = RequestMethod.GET)
        public ModelAndView edit(ModelAndView model){
            model.setViewName("systemuser/userUpdateForm");
            return model;
        }
        //test
        @RequestMapping(value="/insert",method=RequestMethod.POST)
//        转换为json格式
        @ResponseBody
        public Map<String, String> add(User user){
            Map<String, String> ret = new HashMap<String, String>();
            if(user == null){
                ret.put("type", "error");
                ret.put("msg", "数据绑定出错");
                System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
                return ret;
            }
            //判断是否已存在该用户
            String existUser=userService.findUserinfo(user.getUsername());
            System.out.println(existUser);
            if(existUser!=null){
                ret.put("type", "error");
                ret.put("msg", "用户名已存在");
                System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
                return ret;
            }
            if(userService.add(user)<=0){
                ret.put("type", "error");
                ret.put("msg", "添加失败");
                System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
                return ret;
            }
            System.out.println(user.getPassword());
            ret.put("type", "success");
            ret.put("msg", "数据添加成功");
            System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈对了");
            return ret;
        }

        /**
         * 修改用户
         * @param user
         * @return
         */
        @RequestMapping(value="/update",method=RequestMethod.POST)
//        转换为json格式
        @ResponseBody
        public Map<String, String> update(User user){
            Map<String, String> ret = new HashMap<String, String>();
            if(user == null){
                ret.put("type", "error");
                ret.put("msg", "数据绑定出错");
                System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
                return ret;
            }
            //判断是否已存在该用户
            String existUser=userService.findUserinfo(user.getUsername());
//        System.out.println(user.getId().toString());
//        System.out.println(existUser);
//        System.out.println(user.getId().toString().equals(existUser));
            if(existUser!=null){
                if(user.getId().toString().equals(existUser)==false){
                    ret.put("type", "error");
                    ret.put("msg", "用户已存在");
                    System.out.println("错了");
                    return ret;
                }
            }
            if(userService.update(user)<=0){
                ret.put("type", "error");
                ret.put("msg", "修改失败");
                System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈错了");
                return ret;
            }
            System.out.println(user.getPassword());
            ret.put("type", "success");
            ret.put("msg", "数据添加成功");
            System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈对了");
            return ret;
        }
        /**
         *
         */
        @RequestMapping("/deleteOne")
        @ResponseBody
        public Map<String, Object> deleteOne(@RequestParam("id") Long id) {
            System.out.println(id);
            Map<String, Object> map = new HashMap();
            userService.deleteOne(id);
            map.put("type", "success");
            return map;
        }

        @RequestMapping(value="/deleteMany",method=RequestMethod.POST)
        @ResponseBody
        public Map<String, String> deleteMany(
                @RequestParam(value="ids[]",required=true) Long[] ids
        ){
            Map<String, String> ret = new HashMap<String, String>();
            if(ids == null){
                ret.put("type", "error");
                ret.put("msg", "请选择数据!");
                return ret;
            }
            String idsString = "";
            for(Long id:ids){
                idsString += id + ",";
            }
            idsString = idsString.substring(0,idsString.length()-1);
            if(userService.deleteMany(idsString) <= 0){
                ret.put("type", "error");
                ret.put("msg", "删除失败!");
                return ret;
            }
            ret.put("type", "success");
            ret.put("msg", "删除成功!");
            return ret;
        }
//第一种
    //    @RequestMapping(value="/add",method=RequestMethod.POST)
    //    @ResponseBody
    //    public Map<String, String> add(User user){
    //        Map<String, String> ret = new HashMap<String, String>();
    //        if(user == null){
    //            ret.put("type", "error");
    //            ret.put("msg", "?????????????????????!");
    //            return ret;
    //        }
    //        if(StringUtils.isEmpty(user.getUsername())){
    //            ret.put("type", "error");
    //            ret.put("msg", "????????????!");
    //            return ret;
    //        }
    //        if(StringUtils.isEmpty(user.getPassword())){
    //            ret.put("type", "error");
    //            ret.put("msg", "?????????!");
    //            return ret;
    //        }
    //        User existUser = userService.findByUserName(user.getUsername());
    //        if(existUser != null){
    //            ret.put("type", "error");
    //            ret.put("msg", "??????????????!");
    //            return ret;
    //        }
    //        if(userService.add(user) <= 0){
    //            ret.put("type", "error");
    //            ret.put("msg", "??????!");
    //            return ret;
    //        }
    //        ret.put("type", "success");
    //        ret.put("msg", "?????!");
    //        return ret;
    //    }

//    第二种
    //@RequestMapping("/insert")
    //@ResponseBody
    //public Map<String, Object> insertStudent(StudentWithGradeClassMajorCollegeDto stuWGCMC) {
    //    Map<String, Object> map = new HashMap();
    //    if (studentService.selectStudentByNum(stuWGCMC.getStuNum()) != null) { //如果修改后的学生学号已存在
    //        map.put("data", "userNmaeExist");
    //    } else {
    //        studentService.insertStudent(stuWGCMC);
    //        map.put("data", "insertSuccess");
    //    }
    //    return map;
    //}


//    @RequestMapping(value="/get_list",method=RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> getList(
//            @RequestParam(value="username",required=false,defaultValue="") String username,
//            Page page
//    ){
//        Map<String, Object> ret = new HashMap<String, Object>();
//        Map<String, Object> queryMap = new HashMap<String, Object>();
//        queryMap.put("username", "%"+username+"%");
//        queryMap.put("offset", page.getOffset());
//        queryMap.put("pageSize", page.getRows());
//        ret.put("rows", userService.findList(queryMap));
//        ret.put("total", userService.getTotal(queryMap));
//        return ret;
//    }

}
