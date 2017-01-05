package com.kaishengit.mapper;

import com.kaishengit.pojo.Dept;

import java.util.List;

public interface DeptMapper {

    Dept findById(Integer id);

    List<Dept> findAll();

}
