package com.kaishengit.controller;

import com.kaishengit.pojo.Account;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/{id:\\d+}")
    public Account showAccount(@PathVariable Integer id,
                               @RequestHeader("User-Agent") String userAgent,
                               @CookieValue(value = "name",required = false,defaultValue = "no set") String name) {

        /*RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://www.baidu.com",String.class);
        System.out.println(result);*/




        System.out.println("UserAgent:" + userAgent);
        System.out.println("Name:" + name);
        Account account = new Account();
        account.setUserName("jack");
        account.setAge(23);
        return account;
    }

}
