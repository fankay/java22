package com.kaishengit.web.admin;

import com.kaishengit.entity.Node;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by jimi_jin on 2016-12-28.
 */
@WebServlet("/admin/node")
public class NodeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Node> nodeList = new TopicService().findAllNode();
        req.setAttribute("nodeList",nodeList);
        forward("admin/node",req,resp);
    }
}
