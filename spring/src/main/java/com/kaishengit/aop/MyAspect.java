package com.kaishengit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AOP的通知类
 */
//@Component
//@Aspect
public class MyAspect {

    @Pointcut("execution(* com.kaishengit.service..*.*(..))")
    public void pt() {}

    @Before("pt()")
    public void beforeAdvice() {
        System.out.println("前置通知");
    }

    @AfterReturning(value = "pt()",returning = "result")
    public void afterAdvice(Object result) {
        System.out.println("后置通知 : " + result);
    }

    @AfterThrowing(value = "pt()",throwing = "ex")
    public void exceptionAdvice(Exception ex) {
        System.out.println("异常通知 :" + ex.getMessage());
    }

    @After("pt()")
    public void finallyAdvice() {
        System.out.println("最终通知");
    }

    @Around("pt()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("~~前置~~");
            Object result = joinPoint.proceed(); //目标对象方法的执行
            System.out.println("~~后置~~");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("~~异常~~");
        } finally {
            System.out.println("~~最终~~");
        }
    }

}
