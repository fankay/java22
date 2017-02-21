package com.kaishengit.mapper;

import com.kaishengit.pojo.Disk;

import java.util.List;

public interface DiskMapper {

    List<Disk> findByFid(Integer fid);

    void save(Disk disk);
}
