package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.DeviceMapper;
import com.kaishengit.mapper.DeviceRentDetailMapper;
import com.kaishengit.mapper.DeviceRentDocMapper;
import com.kaishengit.mapper.DeviceRentMapper;
import com.kaishengit.pojo.*;
import com.kaishengit.service.DeviceService;
import com.kaishengit.shiro.ShiroUtil;
import com.kaishengit.util.SerialNumberUtil;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DeviceServiceImpl implements DeviceService {

    private Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceRentMapper rentMapper;
    @Autowired
    private DeviceRentDetailMapper rentDetailMapper;
    @Autowired
    private DeviceRentDocMapper rentDocMapper;
    @Value("${upload.path}")
    private String fileSavePath;

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
    @Transactional
    public String saveRent(DeviceRentDto deviceRentDto) {
        //1. 保存合同
        DeviceRent rent = new DeviceRent();
        rent.setAddress(deviceRentDto.getAddress());
        rent.setBackDate(deviceRentDto.getBackDate());
        rent.setCardNum(deviceRentDto.getCardNum());
        rent.setCompanyName(deviceRentDto.getCompanyName());
        rent.setCreateUser(ShiroUtil.getCurrentUserName());
        rent.setFax(deviceRentDto.getFax());
        rent.setLastCost(0F);
        rent.setTel(deviceRentDto.getTel());
        rent.setLinkMan(deviceRentDto.getLinkMan());
        rent.setPreCost(0F);
        rent.setTotalPrice(0F);
        rent.setRentDate(deviceRentDto.getRentDate());
        rent.setTotalDay(deviceRentDto.getTotalDate());
        rent.setSerialNumber(SerialNumberUtil.getSerialNumber());

        rentMapper.save(rent);
        //2. 保存合同详情
        List<DeviceRentDto.DeviceArrayBean> deviceArray  = deviceRentDto.getDeviceArray();
        List<DeviceRentDetail> detailList = Lists.newArrayList();
        float total = 0F;
        for(DeviceRentDto.DeviceArrayBean bean : deviceArray) {
            //查询当前设备库存是否足够
            Device device = deviceMapper.findById(bean.getId());
            if(device.getCurrentNum() < bean.getNum()) {
                throw new ServiceException(device.getName()+"库存不足");
            } else {
                device.setCurrentNum(device.getCurrentNum() - bean.getNum());
                deviceMapper.updateCurrentNum(device);
            }


            DeviceRentDetail rentDetail = new DeviceRentDetail();
            rentDetail.setDeviceName(bean.getName());
            rentDetail.setTotalPrice(bean.getTotal());
            rentDetail.setDevicePrice(bean.getPrice());
            rentDetail.setDeviceUnit(bean.getUnit());
            rentDetail.setNum(bean.getNum());
            rentDetail.setRentId(rent.getId());

            detailList.add(rentDetail);

            total += bean.getTotal();
        }
        if(!detailList.isEmpty()) {
            rentDetailMapper.batchSave(detailList);
        }

        //计算合同总价及预付款、尾款
        total = total * rent.getTotalDay();
        float preCost = total  * 0.3F;
        float lastCost = total - preCost;
        rentMapper.updateCost(total,preCost,lastCost,rent.getId());

        //3. 保存文件
        List<DeviceRentDto.DocBean> docBeanList = deviceRentDto.getFileArray();
        List<DeviceRentDoc> rentDocList = Lists.newArrayList();
        for(DeviceRentDto.DocBean doc : docBeanList) {
            DeviceRentDoc rentDoc = new DeviceRentDoc();
            rentDoc.setRentId(rent.getId());
            rentDoc.setNewName(doc.getNewName());
            rentDoc.setSourceName(doc.getSourceName());

            rentDocList.add(rentDoc);
        }
        if(!rentDocList.isEmpty()) {
            rentDocMapper.batchSave(rentDocList);
        }


        //4. 写入财务流水

        return rent.getSerialNumber();
    }

    @Override
    public DeviceRent findDeviceRentBySerialNumber(String serialNumber) {
        return rentMapper.findBySerialNumber(serialNumber);
    }

    /**
     * 根据设备租赁合同ID查询详情列表
     * @param id
     * @return
     */
    @Override
    public List<DeviceRentDetail> findDeviceRentDetailListByRentId(Integer id) {
        return rentDetailMapper.findByRentId(id);
    }

    @Override
    public List<DeviceRentDoc> findDeviceRentDocListByRentId(Integer id) {
        return rentDocMapper.findByRentId(id);
    }

    @Override
    public InputStream downloadFile(Integer docId) throws IOException {
        DeviceRentDoc doc = rentDocMapper.findById(docId);
        if(doc == null) {
            return null;
        } else {
            File file = new File(new File(fileSavePath),doc.getNewName());
            if(file.exists()) {
                return new FileInputStream(file);
            } else {
                return null;
            }
        }
    }

    @Override
    public DeviceRentDoc findDeviceRentDocById(Integer id) {
        return rentDocMapper.findById(id);
    }

    @Override
    public DeviceRent findDeviceRentById(Integer id) {
        return rentMapper.findById(id);
    }

    @Override
    public void downloadZipFile(DeviceRent rent, ZipOutputStream zipOutputStream) throws IOException {
        //查找合同有多少个合同附件
        List<DeviceRentDoc> deviceRentDocs = findDeviceRentDocListByRentId(rent.getId());
        for(DeviceRentDoc doc : deviceRentDocs) {
            ZipEntry entry = new ZipEntry(doc.getSourceName());
            zipOutputStream.putNextEntry(entry);

            InputStream inputStream = downloadFile(doc.getId());
            IOUtils.copy(inputStream,zipOutputStream);
            inputStream.close();
        }

        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    @Override
    public List<DeviceRent> findDeviceRentByQueryParam(Map<String, Object> queryParam) {
        return rentMapper.findByQueryParam(queryParam);
    }

    @Override
    public Long countOfDeviceRent() {
        return rentMapper.count();
    }

    @Override
    @Transactional
    public void changeRentState(Integer id) {
        //1. 将合同修改为已完成
        DeviceRent deviceRent = findDeviceRentById(id);
        deviceRent.setState("已完成");
        rentMapper.updateState(deviceRent);
        //2. 向财务模块添加尾款记录
    }
}
