package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Admin;
import com.kaishengit.service.AdminService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jimi_jin on 2016-12-28.
 */
@WebServlet("/admin/login")
public class AdminLoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断当前是否有用户
        req.getSession().removeAttribute("curr_admin");
        forward("admin/login",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminName = req.getParameter("adminName");
        String password = req.getParameter("password");
        String ip = req.getRemoteAddr();

        AdminService adminService = new AdminService();
        JsonResult jsonResult = new JsonResult();
        try {
            Admin admin = adminService.login(adminName, password, ip);
            req.getSession().setAttribute("curr_admin",admin);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch (Exception e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());
        }
        renderJSON(jsonResult,resp);
    }
}
