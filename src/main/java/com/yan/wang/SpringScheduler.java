package com.yan.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ywang on 15.08.17.
 */
public class SpringScheduler {
    public static void main(String[] args) {
        // how to run target/
        // mvn clean package
        // java -jar .jar
        ApplicationContext context = SpringApplication.run(AppConfig.class);
    }
}
