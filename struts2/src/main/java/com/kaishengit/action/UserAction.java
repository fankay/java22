package com.kaishengit.action;


import com.kaishengit.entity.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import java.util.Arrays;
import java.util.List;

@Namespace("/user")
public class UserAction extends BaseAction {

    private String code;
    private User user;
    private List<String> nameList;
    private String name;

    @Action("list")
    @Override
    public String execute() throws Exception {
        System.out.println("hello,User");
        return ERROR;
    }

    @Action("save")
    public String save() {
        code = "1009";

        user = new User();
        user.setUserName("张思祺");
        user.setAddress("北京");

        nameList = Arrays.asList("AA","BB","CC");
        name = "Jack";

        return SUCCESS;
    }

    //get set

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
