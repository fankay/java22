package com.kaishengit.web.admin;

import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Topic;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AdminService;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jimi_jin on 2016-12-28.
 */
@WebServlet("/admin/topic")
public class AdminTopicServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;

        TopicService topicService = new TopicService();
        Page<Topic> page = topicService.findAllTopics("",pageNo);
        req.setAttribute("page",page);
        forward("admin/topic",req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        AdminService adminService = new AdminService();
        try {
            adminService.deleteTopicById(id);
            renderText("success",resp);
        }catch (ServiceException e){
            renderText(e.getMessage(),resp);
        }
    }
}
