package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkemail")
public class CheckEmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");

        System.out.println("EMail:" + mail);

        PrintWriter out = resp.getWriter();
        if("aa@qq.com".equals(mail)) {
            out.print("false");
        } else {
            out.print("true");
        }

        out.flush();
        out.close();

    }
}
