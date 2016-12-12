package com.kaishengit.web;

import com.kaishengit.entity.User;
import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/qiniu2")
public class QiniuServlet2 extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ak = "inOypU6O-rKyxyN22Z7Cq1uHMdc4YmhjE1GfJh7L";
        String sk = "l342cQNqBu2GSVUmhz_2by7yBPKJ7foeDH_0tN5r";
        String bucketName = "demo22";

        Auth auth = Auth.create(ak,sk);

        //获取Session中的对象
       /* HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();*/


        String token = auth.uploadToken(bucketName);

        req.setAttribute("id",11223);
        req.setAttribute("token",token);
        req.getRequestDispatcher("uploer_qiniu.jsp").forward(req,resp);


    }
}
