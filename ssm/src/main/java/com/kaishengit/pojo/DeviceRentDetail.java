package com.kaishengit.pojo;

import lombok.Data;

@Data
public class DeviceRentDetail {

    private Integer id;
    private String deviceName;
    private String deviceUnit;
    private Float devicePrice;
    private Integer num;
    private Float totalPrice;
    private Integer rentId;

}
