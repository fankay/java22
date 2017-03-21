package com.kaishengit.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message","Hello,SpringBoot!");

        String name = "jack";
        List<String> nameList = Arrays.asList("AA","BB","CC");

        model.addAttribute("name",name);
        model.addAttribute("nameList",nameList);

        return "index";
    }

}
