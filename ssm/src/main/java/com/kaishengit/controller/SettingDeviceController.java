package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.Device;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 系统设置中的设备管理控制器
 */
@Controller
@RequestMapping("/setting/device")
public class SettingDeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String list() {
        return "setting/device/list";
    }

    @PostMapping("/load")
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String orderIndex = request.getParameter("order[0][column]");
        String orderType = request.getParameter("order[0][dir]");
        String orderColumn = request.getParameter("columns["+orderIndex+"][name]");
        String deviceName = request.getParameter("deviceName");


        Map<String,Object> searchParam = Maps.newHashMap();
        searchParam.put("start",start);
        searchParam.put("length",length);
        searchParam.put("orderType",orderType);
        searchParam.put("orderColumn",orderColumn);
        searchParam.put("deviceName",deviceName);


        List<Device> deviceList = deviceService.findDeviceBySearchParam(searchParam);
        Long count = deviceService.count();
        Long filteredCount = deviceService.countBySearchParam(searchParam);

        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("draw",draw);
        resultMap.put("recordsTotal",count); //总记录数
        resultMap.put("recordsFiltered",filteredCount); //过滤后的总记录数
        resultMap.put("data",deviceList);
        return resultMap;
    }


    @GetMapping("/new")
    public String newDevice() {
        return "setting/device/new";
    }

    @PostMapping("/new")
    public String newDevice(Device device, RedirectAttributes redirectAttributes) {
        deviceService.saveNewDevice(device);
        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/setting/device";
    }

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public String delDevice(@PathVariable Integer id) {
        deviceService.delDevice(id);
        return "success";
    }

}
