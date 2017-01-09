package com.kaishengit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectInvocationHandler implements InvocationHandler {

    private Object target;
    public SubjectInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("sayHello".equals(method.getName())) {
            Object result = null;
            try {
                System.out.println("--before--");
                result = method.invoke(target, args);//代表目标对象方法的执行
                System.out.println("--after--");
            } catch (Exception ex) {
                System.out.println("--excepiton--");
            } finally {
                System.out.println("--finally--");
            }
            return result;
        } else {
            return method.invoke(target,args);
        }
    }
}
