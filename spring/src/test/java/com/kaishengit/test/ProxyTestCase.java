package com.kaishengit.test;

import com.kaishengit.proxy.*;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class ProxyTestCase {

    @Test
    public void proxy() {
       Subject subject = new SubjectProxy();
       subject.sayHello();
    }

    @Test
    public void jdkProxy() {
        RealSubject realSubject = new RealSubject();
        SubjectInvocationHandler subjectInvocationHandler = new SubjectInvocationHandler(realSubject);

        Subject subject = (Subject) Proxy.newProxyInstance(
                realSubject.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),subjectInvocationHandler);

        subject.sayHello();
        subject.save();
    }

    @Test
    public void cglibProxy() {

        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new TargetMethodInterceptor());
        enhancer.setSuperclass(Target.class);

        Target target = (Target) enhancer.create();//父类指向子类对象
        target.save();
        target.update();


    }
}
