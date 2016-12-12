package com.kaishengit.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/upload2")
@MultipartConfig
public class Upload2Servlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        InputStream inputStream = part.getInputStream();

        String uuid = UUID.randomUUID().toString();
        File file = new File("D:/upload/",uuid);
        FileOutputStream outputStream = new FileOutputStream(file);

        IOUtils.copy(inputStream,outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();

        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        // http://localhost:8080/img?file=23i42oi3u293847
        result.put("file_path","http://localhost:8080/img?file="+uuid);

        renderJSON(result,resp);

    }
}
