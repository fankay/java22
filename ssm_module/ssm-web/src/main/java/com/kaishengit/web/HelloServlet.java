package com.kaishengit.web;

import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        User user = new User();
        user.setId(100);
        user.setName("Jack");

        userService.save(user);

        resp.sendRedirect("hello.jsp");
    }
}
