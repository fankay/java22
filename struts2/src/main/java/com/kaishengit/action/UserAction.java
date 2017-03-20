package com.kaishengit.action;

import com.kaishengit.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAction extends BaseAction {

    public UserAction() {
        System.out.println("create userAction...");
    }

    private User user;
    private List<String> names;

    public String execute() {
        names = Arrays.asList("aa","bb","cc");
        return SUCCESS;
    }

    public String save() {
        System.out.println("userName:" + user.getUserName() + " address:" + user.getAddress());
        return "success";
    }

    //get set


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
