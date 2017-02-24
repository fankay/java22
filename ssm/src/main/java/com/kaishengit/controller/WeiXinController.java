package com.kaishengit.controller;

import com.kaishengit.service.WeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
