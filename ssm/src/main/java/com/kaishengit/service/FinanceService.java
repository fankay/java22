package com.kaishengit.service;

import com.kaishengit.pojo.Finance;

import java.util.List;
import java.util.Map;

public interface FinanceService {

    List<Finance> findByQueryParam(Map<String, Object> queryParam);

    Long count();

    void confirmById(Integer id);

    Long filterCount(Map<String, Object> queryParam);

    List<Finance> findByCreatDate(String today);
}
