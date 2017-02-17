package com.kaishengit.dto;

import lombok.Data;

@Data
public class AjaxResult {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String status;
    private String message;
    private Object data;

    public AjaxResult(){}
    public AjaxResult(String status,String message) {
        this.status = status;
        this.message = message;
    }

    public AjaxResult(Object data) {
        this.status = SUCCESS;
        this.data = data;
    }

}
