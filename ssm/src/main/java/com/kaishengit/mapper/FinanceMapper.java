package com.kaishengit.mapper;

import com.kaishengit.pojo.Finance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FinanceMapper {

    void save(Finance finance);

    Long count();

    List<Finance> findByQueryParam(Map<String, Object> queryParam);

    Finance findById(Integer id);

    void updateState(Finance finance);

    Long filterCount(Map<String, Object> queryParam);

    List<Finance> findByCreateDate(String today);

    List<Map<String,Object>> findPieData(@Param("today") String today,@Param("type") String type);

}
