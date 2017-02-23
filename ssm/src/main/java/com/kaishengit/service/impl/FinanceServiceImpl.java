package com.kaishengit.service.impl;

import com.kaishengit.mapper.FinanceMapper;
import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import com.kaishengit.shiro.ShiroUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceMapper financeMapper;

    @Override
    public List<Finance> findByQueryParam(Map<String, Object> queryParam) {
        return financeMapper.findByQueryParam(queryParam);
    }

    @Override
    public Long count() {
        return financeMapper.count();
    }

    /**
     * 确认财务流水
     * @param id
     */
    @Override
    public void confirmById(Integer id) {
        Finance finance = financeMapper.findById(id);
        if(finance != null) {
            finance.setState(Finance.STATE_OK);
            finance.setConfirmDate(DateTime.now().toString("yyyy-MM-dd"));
            finance.setConfirmUser(ShiroUtil.getCurrentUserName());
            financeMapper.updateState(finance);
        }
    }

    @Override
    public Long filterCount(Map<String, Object> queryParam) {
        return financeMapper.filterCount(queryParam);
    }

    @Override
    public List<Finance> findByCreatDate(String today) {
        return financeMapper.findByCreateDate(today);
    }
}
