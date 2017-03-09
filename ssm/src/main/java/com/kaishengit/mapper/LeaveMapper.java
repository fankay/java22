package com.kaishengit.mapper;

import com.kaishengit.pojo.Leave;

/**
 * Created by jimi_jin on 2017-3-9.
 */
public interface LeaveMapper {
    void save(Leave leave);
    void delete(long id);
    Leave getLeaveById(long id);
    void update(Leave leave);
}
