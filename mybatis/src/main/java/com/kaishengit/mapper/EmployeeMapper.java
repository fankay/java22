package com.kaishengit.mapper;

import com.kaishengit.pojo.Employee;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {

    Employee findById(Integer id);

    List<Employee> findByDeptId(Integer deptId);

    @Select("select * from t_employee")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "empname",property = "empname"),
            @Result(column = "deptid",property = "deptid"),
            @Result(column = "deptid",property = "dept",
                    one = @One(select = "com.kaishengit.mapper.DeptMapper.findById"))
    })
    List<Employee> findAll();

}
