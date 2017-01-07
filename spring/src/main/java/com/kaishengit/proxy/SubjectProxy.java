package com.kaishengit.proxy;

public class SubjectProxy implements Subject {
    private RealSubject realSubject = new RealSubject();

    @Override
    public void sayHello() {
        System.out.println("开启事务");
        try {
            realSubject.sayHello();
            System.out.println("提交事务");
        } catch (Exception ex) {
            System.out.println("回滚事务");
        }

    }

    @Override
    public void save() {

    }
}
