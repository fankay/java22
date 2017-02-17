package com.kaishengit.service.impl;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.mapper.DeviceMapper;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.User;
import com.kaishengit.service.DeviceService;
import com.kaishengit.shiro.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    private Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void saveNewDevice(Device device) {
        //让当前库存数量和总数量相同
        device.setCurrentNum(device.getTotalNum());
        deviceMapper.save(device);

        logger.info("{}添加了新设备{}", ShiroUtil.getCurrentUserName(),device.getName());
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

    @Override
    public Device findDeviceById(Integer id) {
        return deviceMapper.findById(id);
    }

    @Override
    public void saveRent(DeviceRentDto deviceRentDto) {
        //xxx
    }
}
