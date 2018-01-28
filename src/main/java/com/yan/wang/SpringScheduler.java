package com.yan.wang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yan.wang.entity.TickerHour;
import com.yan.wang.service.TickerHourService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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

    @Scheduled(fixedRate = 1800000, initialDelay = 3000)
    public void getPriceAndVolume () {
        if (tHS != null) {
            System.out.println(tHS.getValueForTest());

            ObjectMapper mapper = new ObjectMapper();

            try {
                TickerHour tickerHourBtcUsd = mapper.readValue(new URL("https://www.bitstamp.net/api/v2/ticker_hour/btcusd/"), TickerHour.class);
                tickerHourBtcUsd.setCryptoCurrency("BTC");
                TickerHour tickerHourEthUsd = mapper.readValue(new URL("https://www.bitstamp.net/api/v2/ticker_hour/ethusd/"), TickerHour.class);
                tickerHourEthUsd.setCryptoCurrency("ETH");
                tHS.add(tickerHourBtcUsd);
                tHS.add(tickerHourEthUsd);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
