package com.kaishengit.action;

public class AccountAction extends BaseAction {

    private String userName;
    private String password;
    private String code;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String login() {
        if("tom".equals(userName) && "123123".equals(password)) {
            getSession().put("current_user","tom");
            return SUCCESS;
        } else {
            code = "1002";
            return ERROR;
        }
    }


    //get set

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
