package com.yan.wang.service;

import com.yan.wang.entity.TickerHour;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TickerHourService {

    void add(TickerHour tickerHour);
    List<TickerHour> listTickerHour();
    String getValueForTest();

}
