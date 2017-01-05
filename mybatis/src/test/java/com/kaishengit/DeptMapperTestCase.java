package com.kaishengit;

import com.kaishengit.mapper.DeptMapper;
import com.kaishengit.mapper.EmployeeMapper;
import com.kaishengit.pojo.Dept;
import com.kaishengit.pojo.Employee;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class DeptMapperTestCase {

    @Test
    public void findById() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();

        DeptMapper deptMapper = session.getMapper(DeptMapper.class);
        //EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);


        Dept dept = deptMapper.findById(12);
        System.out.println(dept);

        List<Employee> employeeList = dept.getEmployeeList();

        for(Employee employee : employeeList) {
            System.out.println(employee);
        }


/*
        System.out.println(dept);
        List<Employee> employeeList = employeeMapper.findByDeptId(12);

        for(Employee employee : employeeList) {
            System.out.println(employee);
        }*/



        session.close();
    }

    @Test
    public void findAll() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();

        DeptMapper deptMapper = session.getMapper(DeptMapper.class);
        List<Dept> deptList = deptMapper.findAll();

        for(Dept dept : deptList) {
            System.out.println(dept);
            List<Employee> employeeList = dept.getEmployeeList();
            for(Employee employee : employeeList) {
                System.out.println(employee);
            }
            System.out.println("----------------------------------");
        }


        session.close();
    }
}
