package com.kaishengit.action;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class GsonAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        User user = new User();
        user.setAddress("中国");
        user.setUserName("李斯");

        renderJson(user);

        return NONE;
    }
}
