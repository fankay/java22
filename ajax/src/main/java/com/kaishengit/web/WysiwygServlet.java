package com.kaishengit.web;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wysiwyg")
public class WysiwygServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ak = "inOypU6O-rKyxyN22Z7Cq1uHMdc4YmhjE1GfJh7L";
        String sk = "l342cQNqBu2GSVUmhz_2by7yBPKJ7foeDH_0tN5r";
        String bucketName = "demo22";

        Auth auth = Auth.create(ak,sk);

        String returnBody = "{\"success\":true,\"file_path\":\"http://ohwnpkfcx.bkt.clouddn.com/${key}\"}";

        StringMap map = new StringMap();
        map.put("returnBody",returnBody);

        String token = auth.uploadToken(bucketName,null,3600,map);

        req.setAttribute("token",token);
        req.getRequestDispatcher("wysiwyg.jsp").forward(req,resp);



    }
}
