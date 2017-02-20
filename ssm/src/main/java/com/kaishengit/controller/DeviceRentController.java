package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDoc;
import com.kaishengit.service.DeviceService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String list() {
        return "device/rent/list";
    }

    @GetMapping("/load")
    @ResponseBody
    public DataTablesResult load(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("start",start);
        queryParam.put("length",length);

        List<DeviceRent> deviceRentList = deviceService.findDeviceRentByQueryParam(queryParam);
        Long count = deviceService.countOfDeviceRent();

        return new DataTablesResult(draw,count,count,deviceRentList);
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

        try {
            String serialNumber = deviceService.saveRent(deviceRentDto);
            AjaxResult result = new AjaxResult();
            result.setData(serialNumber);
            result.setStatus(AjaxResult.SUCCESS);
            return result;
        } catch (ServiceException ex) {
            return new AjaxResult(AjaxResult.ERROR,ex.getMessage());
        }
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

    @GetMapping("/doc")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws IOException {
        InputStream inputStream = deviceService.downloadFile(id);
        if(inputStream == null) {
            throw new NotFoundException();
        } else {
            DeviceRentDoc doc = deviceService.findDeviceRentDocById(id);
            String fileName = doc.getSourceName();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",fileName, Charset.forName("UTF-8"));
            return new ResponseEntity<>(new InputStreamResource(inputStream),headers, HttpStatus.OK);
        }
    }

    /**
     * 合同文件的打包下载
     * @param id
     */
    @GetMapping("/doc/zip")
    public void downloadZipFile(Integer id,HttpServletResponse response) throws IOException {
        DeviceRent rent = deviceService.findDeviceRentById(id);
        if(rent == null) {
            throw new NotFoundException();
        } else {
            //将文件下载标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载的名称
            String fileName = rent.getCompanyName()+".zip";
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            deviceService.downloadZipFile(rent,zipOutputStream);
        }
    }


    /*@GetMapping("/doc")
    public void downloadFile(Integer id, HttpServletResponse response) throws IOException {
        InputStream inputStream = deviceService.downloadFile(id);
        if(inputStream == null) {
            throw new NotFoundException();
        } else {
            DeviceRentDoc doc = deviceService.findDeviceRentDocById(id);
            //将文件下载标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载的名称
            String fileName = doc.getSourceName();
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }*/


    /**
     * 将合同状态修改为已完成
     * @return
     */
    @PostMapping("/state/change")
    @ResponseBody
    public AjaxResult changeRentState(Integer id) {
        deviceService.changeRentState(id);
        return new AjaxResult(AjaxResult.SUCCESS);
    }


}
