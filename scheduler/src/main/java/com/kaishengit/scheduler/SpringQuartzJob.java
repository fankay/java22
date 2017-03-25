package com.kaishengit.scheduler;

import org.springframework.stereotype.Component;

@Component
public class SpringQuartzJob {

    public void execute() {
        System.out.println("Spring Quartz Job...");
    }
}
