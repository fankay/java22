package com.kaishengit.web;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeid = req.getParameter("nodeid");
        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        if (!StringUtils.isEmpty(nodeid) && !StringUtils.isNumeric(nodeid)){
            forward("index",req,resp);
            return;
        }
        TopicService topicService = new TopicService();
        List<Node> nodeList = topicService.findAllNode();

        Page<Topic> page = topicService.findAllTopics(nodeid,pageNo);

        req.setAttribute("page",page);
        req.setAttribute("nodeList",nodeList);
        forward("index",req,resp);
    }
}
