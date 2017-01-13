package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Device implements Serializable {

    private Integer id;
    private String name;
    private String unit;
    private Integer totalNum;
    private Integer currentNum;
    private Float price;

}
