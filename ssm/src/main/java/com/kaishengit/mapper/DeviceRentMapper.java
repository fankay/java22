package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRent;
import org.apache.ibatis.annotations.Param;

public interface DeviceRentMapper {
    void save(DeviceRent rent);

    void updateCost(@Param("total") float total,
                    @Param("preCost") float preCost,
                    @Param("lastCost") float lastCost,
                    @Param("id") Integer id);

    DeviceRent findBySerialNumber(String serialNumber);

    DeviceRent findById(Integer id);
}
