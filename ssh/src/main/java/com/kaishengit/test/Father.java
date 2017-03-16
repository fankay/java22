package com.kaishengit.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Father <T> {

    public Father() {
        System.out.println("create father:" + this);

        Class clazz = this.getClass();//Son
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();//Father<Movie>
        Type[] types = parameterizedType.getActualTypeArguments();
        Class paramClass = (Class) types[0];
        System.out.println(paramClass);


    }
}
