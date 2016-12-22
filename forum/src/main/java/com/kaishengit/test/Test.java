package com.kaishengit.test;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by jimi_jin on 2016-12-22.
 */
public class Test {
    public static void main(String[] args) {
        long time = new Date().getTime();
        System.out.println("time = " + time);
        long time1 = System.currentTimeMillis();
        System.out.println("time1 = " + time1);

        DateTime time2 = DateTime.now();
        time2.getMillis();
        System.out.println("time2 = " + time2.getMillis());

    }


}
