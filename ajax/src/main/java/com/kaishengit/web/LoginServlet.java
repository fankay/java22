package com.kaishengit.web;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        Map<String,Object> result = new HashMap<>();

        if("tom".equals(username) && "123123".equals(password)) {
            result.put("state","success");
        } else {
            result.put("state","error");
            result.put("message","账号或密码错误");
        }

        String json = new Gson().toJson(result);
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();



    }
}
