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

    @Scheduled(fixedRate = 3000)
    public void getPriceAndVolume () {
        if (tHS != null) {
            System.out.println(tHS.getValueForTest());

            ObjectMapper mapper = new ObjectMapper();

            try {
                TickerHour tickerHour = mapper.readValue(new URL("https://www.bitstamp.net/api/v2/ticker_hour/btcusd/"), TickerHour.class);
                System.out.println("Timestamp : " + tickerHour.getTimestamp());
                System.out.println("Last : " + tickerHour.getLast());
                System.out.println("Volume : " + tickerHour.getVolume());
                tickerHour.setAsk(10461.00);
                tickerHour.setBid(10450.00);
                tickerHour.setHigh(10461.97);
                tickerHour.setLast(10450.00);
                tickerHour.setLow(10230.06);
                tickerHour.setOpen(10321.84);
                tickerHour.setTimestamp("1516719042");
                tickerHour.setVolume(607.74748386);
                tickerHour.setVwap(10328.77);

                tHS.add(tickerHour);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
