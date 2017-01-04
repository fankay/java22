package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.vo.UserVo;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by jimi_jin on 2016-12-29.
 */
@WebServlet("/admin/user")
public class AdminUserServlet extends BaseServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.parseInt(p):1;
        Page<UserVo> page =userService.findUserList(pageNo);
        req.setAttribute("page",page);
        forward("admin/user",req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //jquery rel 方式获取
        /* String userStateId = req.getParameter("userStateId");
        String[] stateandid = userStateId.split(",");
        String userid = stateandid[1];
        Integer userState = Integer.valueOf(stateandid[0]);*/
        //js方式获取
        Integer userState = Integer.valueOf(req.getParameter("userState"));
        String userid = req.getParameter("userid");
        userState = userState == 1 ? 2:1;
        JsonResult jsonResult = new JsonResult();
        try {
            userService.updateUserState(userid, userState);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());
        }
        renderJSON(jsonResult,resp);
    }
}


