package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDoc;
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
    public AjaxResult saveRent(@RequestBody DeviceRentDto deviceRentDto) {
        String serialNumber = deviceService.saveRent(deviceRentDto);

        AjaxResult result = new AjaxResult();
        result.setData(serialNumber);
        result.setStatus(AjaxResult.SUCCESS);
        return result;
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

    /**
     * 根据流水号显示合同详情
     * @param serialNumber
     * @return
     */
    @GetMapping("/{serialNumber:\\d+}")
    public String showDeviceRent(@PathVariable String serialNumber,Model model) {
        //1.查询合同对象
        DeviceRent deviceRent = deviceService.findDeviceRentBySerialNumber(serialNumber);
        if(deviceRent == null) {
            throw new NotFoundException();
        } else {
            //2.查询合同详情列表
            List<DeviceRentDetail> detailList = deviceService.findDeviceRentDetailListByRentId(deviceRent.getId());
            //3.查询合同文件列表
            List<DeviceRentDoc> docList = deviceService.findDeviceRentDocListByRentId(deviceRent.getId());

            model.addAttribute("rent",deviceRent);
            model.addAttribute("detailList",detailList);
            model.addAttribute("docList",docList);

            return "device/rent/show";
        }
    }


}
