package com.kaishengit.controller;

import com.kaishengit.service.WeixinService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/wx")
public class WeiXinController {

    private Logger logger = LoggerFactory.getLogger(WeiXinController.class);

    @Autowired
    private WeixinService weixinService;

    /**
     * 微信初始化方法
     * @return
     */
    @GetMapping("/init")
    @ResponseBody
    public String init(String msg_signature,String timestamp,String nonce,String echostr) {
        logger.info("{}-{}-{}-{}",msg_signature,timestamp,nonce,echostr);
        return weixinService.init(msg_signature,timestamp,nonce,echostr);
    }

    @GetMapping("/meeting")
    public String meeting(String code,
                          @RequestHeader("User-Agent") String userAgent,
                          HttpServletResponse response) {
        logger.info("userAgent:{}",userAgent);
        String userId = weixinService.codeToUserId(code);
        if(userId == null) {
            logger.error("未知用户访问");
            return "wx/403";
        } else {
            logger.info("{}访问来了",userId);
            Cookie cookie = new Cookie("wxUser",userId);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "wx/meeting";
        }
    }

    @RequestMapping("/abc")
    public String abc(@CookieValue(value = "wxUser",required = false) String userId) {
        if(StringUtils.isEmpty(userId)) {
            logger.error("未知用户访问了abc");
            return "wx/403";
        } else {
            logger.info("成功到达Abc页面");
            return "wx/abc";
        }
    }
}
