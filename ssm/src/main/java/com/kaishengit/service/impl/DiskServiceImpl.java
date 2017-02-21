package com.kaishengit.service.impl;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.DiskMapper;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import com.kaishengit.shiro.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class DiskServiceImpl implements DiskService {
    @Autowired
    private DiskMapper diskMapper;
    @Value("${upload.path}")
    private String savePath;

    @Override
    public List<Disk> findByFid(Integer fid) {
        return diskMapper.findByFid(fid);
    }

    /**
     * 新建文件夹
     * @param disk
     */
    @Override
    public void saveNewFolder(Disk disk) {
        disk.setCreateUser(ShiroUtil.getCurrentUserName());
        disk.setCreateTime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        disk.setType(Disk.DIRECTORY_TYPE);
        diskMapper.save(disk);
    }

    /**
     * 保存文件
     * @param fid
     * @param file
     */
    @Override
    @Transactional
    public void saveNewFile(Integer fid, MultipartFile file) {
        //存文件到磁盘
        String sourceName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString();
        Long size = file.getSize();

        if(sourceName.lastIndexOf(".") != -1) {
            newName += sourceName.substring(sourceName.lastIndexOf("."));
        }

        try {
            File saveFile = new File(new File(savePath), newName);
            FileOutputStream outputStream = new FileOutputStream(saveFile);
            InputStream inputStream = file.getInputStream();
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            throw new ServiceException("文件保存到磁盘异常",ex);
        }



        //保存数据库记录
        Disk disk = new Disk();
        disk.setFid(fid);
        disk.setSourceName(sourceName);
        disk.setName(newName);
        disk.setCreateUser(ShiroUtil.getCurrentUserName());
        disk.setCreateTime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        disk.setType(Disk.FILE_TYPE);
        disk.setSize(FileUtils.byteCountToDisplaySize(size));

        diskMapper.save(disk);
    }
}
