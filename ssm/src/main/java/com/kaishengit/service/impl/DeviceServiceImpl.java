package com.kaishengit.service.impl;

import com.kaishengit.mapper.DeviceMapper;
import com.kaishengit.pojo.Device;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void saveNewDevice(Device device) {
        //让当前库存数量和总数量相同
        device.setCurrentNum(device.getTotalNum());
        deviceMapper.save(device);
    }

    @Override
    public List<Device> findAllDevice() {
        return deviceMapper.findAll();
    }

    @Override
    public List<Device> findDeviceByPage(String start, String length) {
        return deviceMapper.findByPage(start,length);
    }

    @Override
    public Long count() {
        return deviceMapper.count();
    }

    @Override
    public List<Device> findDeviceBySearchParam(Map<String, Object> searchParam) {
        return deviceMapper.findBySeachParam(searchParam);
    }

    @Override
    public void delDevice(Integer id) {
        deviceMapper.del(id);
    }

    @Override
    public Long countBySearchParam(Map<String, Object> searchParam) {
        return deviceMapper.countBySearchParam(searchParam);
    }
}
