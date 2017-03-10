package com.kaishengit.service.impl;

import com.kaishengit.mapper.LeaveMapper;
import com.kaishengit.pojo.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jimi_jin on 2017-3-10.
 */
@Service
@Transactional
public class LeaveManager {

    @Autowired
    LeaveMapper leaveMapper;

    /**
     * 保存实体
     */
    public void save(Leave entity) {
        leaveMapper.save(entity);
    }

    public void delete(Long id) {
        leaveMapper.delete(id);
    }

    public Leave get(Long id) {
        return leaveMapper.getLeaveById(id);
    }

    public void update(Leave entity) {
        leaveMapper.update(entity);
    }
}
