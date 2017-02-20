package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRentDoc;

import java.util.List;

public interface DeviceRentDocMapper {
    void batchSave(List<DeviceRentDoc> rentDocList);

    List<DeviceRentDoc> findByRentId(Integer id);

    DeviceRentDoc findById(Integer docId);
}
