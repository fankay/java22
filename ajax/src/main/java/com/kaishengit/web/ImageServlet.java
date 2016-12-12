package com.kaishengit.web;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/img")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String file = req.getParameter("file");
        if(StringUtils.isEmpty(file)) {
            resp.sendError(404);
        } else {

            File f = new File("D:/upload", file);
            if (!f.exists()) {
                resp.sendError(404);
            } else {
                FileInputStream inputStream = new FileInputStream(f);
                OutputStream outputStream = resp.getOutputStream();

                IOUtils.copy(inputStream,outputStream);
                outputStream.flush();
                outputStream.close();
                inputStream.close();


            }
        }

    }

}
