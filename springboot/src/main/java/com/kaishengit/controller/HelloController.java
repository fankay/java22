package com.kaishengit.controller;


import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String hello(Model model) {
        model.addAttribute("message","Hello,SpringBoot!");

        String name = "jack";
        List<String> nameList = Arrays.asList("AA","BB","CC");

        model.addAttribute("name",name);
        model.addAttribute("nameList",nameList);
        model.addAttribute("userList",userService.findAll());

        return "main";
    }

}
