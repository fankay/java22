package com.kaishengit.mapper;

import com.kaishengit.pojo.Employee;

import java.util.List;

public interface EmployeeMapper {

    Employee findById(Integer id);

    List<Employee> findByDeptId(Integer deptId);

}
