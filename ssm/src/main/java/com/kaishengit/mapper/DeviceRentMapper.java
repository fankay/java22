package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeviceRentMapper {
    void save(DeviceRent rent);

    void updateCost(@Param("total") float total,
                    @Param("preCost") float preCost,
                    @Param("lastCost") float lastCost,
                    @Param("id") Integer id);

    DeviceRent findBySerialNumber(String serialNumber);

    DeviceRent findById(Integer id);

    List<DeviceRent> findByQueryParam(Map<String, Object> queryParam);

    Long count();

    void updateState(DeviceRent deviceRent);
}
