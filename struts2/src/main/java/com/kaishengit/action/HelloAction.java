package com.kaishengit.action;

public class HelloAction {

    private String code;

    public String execute() throws Exception {
        System.out.println("Hello,Struts2");
        return "success";
    }

    public String list() {
        code = "1009";
        System.out.println("List page");
        return "success";
    }

    //get set
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
