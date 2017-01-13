package com.kaishengit.service;

import com.kaishengit.pojo.Device;

import java.util.List;

public interface DeviceService {


    void saveNewDevice(Device device);

    List<Device> findAllDevice();

    List<Device> findDeviceByPage(String start, String length);

    Long count();

}
