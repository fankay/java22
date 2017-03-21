package com.kaishengit.service;

import com.kaishengit.dao.BankDao;
import com.kaishengit.pojo.Bank;
import com.kaishengit.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankDao bankDao;


    @Transactional(readOnly = true)
    public List<Bank> findAll() {
        return bankDao.findAll();
    }

    public void save(Bank bank) {
        bankDao.save(bank);
    }

    public void del(Integer id) {
        bankDao.delete(id);
    }

    public Bank findById(Integer id) {
        return bankDao.findById(id);
    }

    public List<Bank> findByQueryParam(List<QueryParam> queryParamList) {
        return bankDao.findByQueryParam(queryParamList);
    }
}
