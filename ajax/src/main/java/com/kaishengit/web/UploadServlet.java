package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part part = req.getPart("file");
        System.out.println("Size:" + part.getSize());
        String contentType = part.getContentType();

        System.out.println("ContentType:" + contentType);

        Map<String,Object> result = new HashMap<>();
        result.put("state","success");
        result.put("data","1234");

        renderJSON(result,resp);




        //System.out.println("name:" + part.getSubmittedFileName());
    }
}
