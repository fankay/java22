package com.kaishengit;

import com.kaishengit.scheduler.MyQuartzJob;
import com.kaishengit.scheduler.MyTimerTask;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Timer;

public class Test {
    public static void main(String[] args) throws SchedulerException, IOException {

        /*Timer timer = new Timer();
        timer.schedule(new MyTimerTask(),0,1000);*/
/*
        //job
        JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class).build();
        //tirgger
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        scheduleBuilder.withIntervalInSeconds(3);
        scheduleBuilder.repeatForever();
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();*/


      /*  //job
        JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class).usingJobData("title","Hello,Job").build();
        //tirgger
        //cron表达式
        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ? *");
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();*/
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");


        System.in.read();




    }
}
