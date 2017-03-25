package com.kaishengit.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringAnnotationTask {

    @Scheduled(fixedDelay = 2000)
    public void sayHello() {
        System.out.println("sayhello sayHello");
    }

    @Async
    public void sendWeixinMessage() {

    }
}
