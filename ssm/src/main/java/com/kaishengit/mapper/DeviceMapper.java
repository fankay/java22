package com.kaishengit.mapper;

import com.kaishengit.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    void save(Device device);

    List<Device> findAll();

    List<Device> findByPage(@Param("start") String start,@Param("length") String length);

    Long count();
}
