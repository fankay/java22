package com.kaishengit.web.topic;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jimi_jin on 2016-12-23.
 */
@WebServlet("/topicFav")
public class FavServlet extends BaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String topicId = req.getParameter("topicid");
        User user = (User) req.getSession().getAttribute("curr_user");
        TopicService service = new TopicService();
        JsonResult result = new JsonResult();
        if (StringUtils.isNotEmpty(action) && StringUtils.isNumeric(topicId)){
            if(action.equals("fav")){
                service.favTopic(user,topicId);
                result.setState(JsonResult.SUCCESS);
            }else if(action.equals("unfav")){
                service.unfavTopic(user,topicId);
                result.setState(JsonResult.SUCCESS);
            }

            TopicService topicService = new TopicService();
            Topic topic = topicService.findTopicById(topicId);
            result.setData(topic.getFavnum());
        }else{
            result.setMessage("参数异常");
        }
        renderJSON(result,resp);
    }
}
