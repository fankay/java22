package com.kaishengit.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/")
    public String login(String userName, String password, RedirectAttributes redirectAttributes) {
        //Shiro方式登录
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(userName, password));
            return "redirect:/home";
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes) {
        //安全退出
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","你已安全退出");
        return "redirect:/";
    }


    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping("/403")
    public String error403() {
        return "403";
    }
}
