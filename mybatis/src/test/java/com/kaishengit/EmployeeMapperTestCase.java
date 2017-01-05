package com.kaishengit;

import com.kaishengit.mapper.EmployeeMapper;
import com.kaishengit.pojo.Dept;
import com.kaishengit.pojo.Employee;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class EmployeeMapperTestCase {

    @Test
    public void findById() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();

        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findById(23);
        Dept dept = employee.getDept();

        System.out.println(employee);
        System.out.println(dept);

        session.close();

    }

    @Test
    public void findAll() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();

        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employeeList = employeeMapper.findAll();

        for(Employee employee : employeeList) {
            System.out.println(employee);
            Dept dept = employee.getDept();
            System.out.println(dept);
            System.out.println("-----------------------------");
        }


        session.close();
    }
}
