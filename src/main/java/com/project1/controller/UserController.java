package com.project1.controller;
import com.project1.model.User;
import com.project1.service.IUserService;
import com.project1.util.CpachaUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private IUserService userService;
//    一个controller分配一个路径
    @RequestMapping("/gotoLogin")
    public String gotoLogin(){
        return "login";
    }

    @RequestMapping(value = "/get_cpacha",method = RequestMethod.GET)
    public void getCpacha(HttpServletRequest request,HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil(4,120,40);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute("loginCpacha",generatorVCode);
        BufferedImage generatorRotateVCodeImage=cpachaUtil.generatorRotateVCodeImage(generatorVCode,true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value="loginName" ,required=true) String loginName,
                        String password,@RequestParam(value="vcode" ,required=true) String vcode,Model model, HttpServletRequest request,HttpSession session){
        logger.info("登陆用户名:"+loginName);
        //用redirect和forward标签返回
        //注意:用这两个标签必须写全路径,不适应视图解析的逻辑视图
        if(StringUtils.isNotEmpty(loginName)&&
                StringUtils.isNotEmpty(password)){
            User user = userService.loginUser(loginName, password);
            if(user!=null){
                String loginCpacha =(String) request.getSession().getAttribute("loginCpacha");
                if (StringUtils.isEmpty(loginCpacha)) {
                    model.addAttribute("loginFlag","验证码已失效请刷新");
                    return "forward:/WEB-INF/pages/login.jsp";
                }
                if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())){
                    model.addAttribute("loginFlag","验证码错误");
                    return "forward:/WEB-INF/pages/login.jsp";
                }else {
                    logger.info("登陆成功");
                    session.setAttribute("user", user);
                    return "redirect:/main";
                }
            }else{
                model.addAttribute("loginFlag","登陆失败,请输入正确的用户名和密码");
                return "forward:/WEB-INF/pages/login.jsp";
            }
        }else{
            model.addAttribute("loginFlag","登陆失败,请输入正确的用户名和密码");
            return "forward:/WEB-INF/pages/login.jsp";
        }
    }

    @RequestMapping("/main")
    public String main(){
        return "main/main";
    }

}
