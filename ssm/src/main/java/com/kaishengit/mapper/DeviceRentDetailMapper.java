package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRentDetail;

import java.util.List;

public interface DeviceRentDetailMapper {
    void batchSave(List<DeviceRentDetail> detailList);

    List<DeviceRentDetail> findByRentId(Integer rentId);
}
