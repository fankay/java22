package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.Device;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String list() {
        return "device/rent/list";
    }

    /**
     * 新建租赁合同
     * @return
     */
    @GetMapping("/new")
    public String newRent(Model model) {
        List<Device> deviceList = deviceService.findAllDevice();
        model.addAttribute("deviceList",deviceList);
        return "device/rent/new";
    }

    @PostMapping("/new")
    @ResponseBody
    public String saveRent(@RequestBody DeviceRentDto deviceRentDto) {
        deviceService.saveRent(deviceRentDto);
        return "success";
    }

    /**
     * 根据设备ID查找设备信息
     * @param id
     * @return
     */
    @GetMapping("/device.json")
    @ResponseBody
    public AjaxResult deviceJson(Integer id) {
        Device device = deviceService.findDeviceById(id);
        if(device == null) {
            return new AjaxResult(AjaxResult.ERROR,"设备不存在");
        } else {
            return new AjaxResult(device);
        }
    }

}
