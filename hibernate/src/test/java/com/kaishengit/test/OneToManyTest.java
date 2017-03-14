package com.kaishengit.test;

import com.kaishengit.pojo.Dept;
import com.kaishengit.pojo.Employee;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OneToManyTest {

    @Test
    public void save() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Dept dept = new Dept();
        dept.setDeptname("开发部");

        Employee employee = new Employee();
        employee.setEmpname("李四");
        employee.setDept(dept);

        Employee employee2 = new Employee();
        employee2.setEmpname("王五");
        employee2.setDept(dept);

        Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        employees.add(employee2);

        dept.setEmployeeSet(employees);


        //1.先存一，再存多
        //2.让一端放弃关系维护（一）

        session.save(dept);
        session.save(employee);
        session.save(employee2);



        session.getTransaction().commit();
    }

    @Test
    public void findOneToMany() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Dept dept = (Dept) session.get(Dept.class,17);
        System.out.println(dept.getDeptname());

        //懒加载
        Set<Employee> employeeSet = dept.getEmployeeSet();
        for(Employee employee : employeeSet) {
            System.out.println(employee.getEmpname());
        }


        session.getTransaction().commit();
    }

    @Test
    public void findManyToOne() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        //N+1
        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> employeeList = criteria.list();

        for(Employee employee : employeeList) {
            System.out.println(employee.getEmpname() + "->" + employee.getDept().getDeptname());
        }


        /*Employee employee = (Employee) session.get(Employee.class,31);
        System.out.println(employee.getEmpname());
        //懒加载
        System.out.println(employee.getDept().getDeptname());*/

        session.getTransaction().commit();
    }

    @Test
    public void delete() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Dept dept = (Dept) session.get(Dept.class,16);
        session.delete(dept);

        session.getTransaction().commit();
    }

}
