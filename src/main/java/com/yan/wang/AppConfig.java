package com.yan.wang;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by ywang on 15.08.17.
 */
@Configuration
@ComponentScan(basePackages="com.yan.wang")
//@EnableScheduling
public class AppConfig {
    //@Scheduled(fixedRate=1500)
    public void doTask() {
        System.out.println("Do Task...");
    }
}
