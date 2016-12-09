package com.kaishengit.web;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/qiniucallback")
public class QiniuCallbackServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadRet = req.getParameter("upload_ret");
        if(StringUtils.isEmpty(uploadRet)) {
            resp.sendError(404);
        } else {
            //Base64解密
            String result = new String(Base64.decodeBase64(uploadRet));
            System.out.println(result);

            //将JSON转换为Map对象
            Gson gson = new Gson();
            Map<String,Object> map = gson.fromJson(result, HashMap.class);
            String fileName = (String)map.get("key");

            req.setAttribute("fileName",fileName);
            req.getRequestDispatcher("qiniu.jsp").forward(req,resp);

        }
    }
}
