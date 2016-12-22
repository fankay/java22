package com.kaishengit.web.topic;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.Config;
import com.kaishengit.web.BaseServlet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by jimi_jin on 2016-12-20.
 */

@WebServlet("/newTopic")
public class NewTopicServlet extends BaseServlet {
    TopicService service = new TopicService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Auth auth = Auth.create(Config.get("qiniu.ak"),Config.get("qiniu.sk"));
        StringMap stringMap = new StringMap();
        stringMap.put("returnBody","{ \"success\": true,\"file_path\": \""+Config.get("qiniu.domain")+"${key}\"}");
        String token = auth.uploadToken(Config.get("qiniu.bucket"),null,3600,stringMap);

        //获取nodelist到jsp页面
        List<Node> nodelist = service.findAllNode();
        req.setAttribute("nodeList",nodelist);
        req.setAttribute("token",token);
        forward("topic/newTopic",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nodeid = req.getParameter("nodeid");
        User user = (User)req.getSession().getAttribute("curr_user");
        Topic topic = null;
        JsonResult jsonResult = null;
        try {
            topic = service.addNewTopic(title, content, Integer.valueOf(nodeid), user.getId());
            jsonResult = new JsonResult(topic);
        }catch (ServiceException e){
            jsonResult = new JsonResult(e.getMessage());
        }

        renderJSON(jsonResult,resp);
    }
}
