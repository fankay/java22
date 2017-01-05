package com.kaishengit.mapper;

import com.kaishengit.pojo.Dept;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptMapper {

    Dept findById(Integer id);

    @Select("select * from t_dept")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "deptname",property = "deptname"),
            @Result(column = "id",property = "employeeList",
                    many = @Many(select = "com.kaishengit.mapper.EmployeeMapper.findByDeptId"))
    })
    List<Dept> findAll();

}
