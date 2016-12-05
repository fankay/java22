package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        name = new String(name.getBytes("ISO8859-1"),"UTF-8"); //URL中含有中文

        //声明禁止浏览器缓存结果的响应头
        resp.setHeader("pragma","no-cache");
        resp.setHeader("cache-control","no-cache");
        resp.setHeader("expries","0");


        System.out.println("Hello,Ajax!!! -> " + name);

        PrintWriter out = resp.getWriter();
        out.print("Hello,World");
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //表单提交(POST)含有中文
        req.setCharacterEncoding("UTF-8");

        //给客户端设置响应编码
        resp.setCharacterEncoding("UTF-8");
        //设置响应内容(纯字符串)
        resp.setContentType("text/plain;charset=UTF-8");

        String name = req.getParameter("name");

        PrintWriter out = resp.getWriter();
        if("tom".equals(name)) {
            System.out.println("账号被占用");
            out.print("不能用");
        } else {
            System.out.println("账号可以使用");
            out.print("能用");
        }

        System.out.println("Hello,Ajax doPost -> " + name);

        out.flush();
        out.close();
    }
}
