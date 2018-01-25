package com.yan.wang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yan.wang.entity.TickerHour;
import com.yan.wang.service.TickerHourService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.net.URL;

/**
 * Created by ywang on 15.08.17.
 */
@Configuration
@EnableAutoConfiguration
@EnableScheduling
public class SpringScheduler {

    static TickerHourService tHS;

    public static void main(String[] args) {
        // how to run target/
        // mvn clean package
        // java -jar .jar
        ApplicationContext context = SpringApplication.run(AppConfig.class);
        TickerHourService tickerHourService = context.getBean(TickerHourService.class);
        tHS = tickerHourService;
    }

    @Scheduled(fixedRate = 180000, initialDelay = 3000)
    public void getPriceAndVolume () {
        if (tHS != null) {
            System.out.println(tHS.getValueForTest());

            ObjectMapper mapper = new ObjectMapper();

            try {
                TickerHour tickerHour = mapper.readValue(new URL("https://www.bitstamp.net/api/v2/ticker_hour/btcusd/"), TickerHour.class);
                System.out.println("Timestamp : " + tickerHour.getTimestamp());
                System.out.println("Last : " + tickerHour.getLast());
                System.out.println("Volume : " + tickerHour.getVolume());
                tHS.add(tickerHour);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
