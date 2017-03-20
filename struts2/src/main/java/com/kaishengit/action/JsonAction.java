package com.kaishengit.action;

import com.kaishengit.entity.User;

public class JsonAction extends BaseAction {

    private Integer pid;
    private User user;

    @Override
    public String execute() throws Exception {
        user = new User();
        user.setUserName("王五");
        //user.setAddress("新西兰");

        return SUCCESS;
    }

    //get set


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
