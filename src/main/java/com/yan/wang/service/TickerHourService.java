package com.yan.wang.service;

import com.yan.wang.entity.TickerHourBtcUsd;
import com.yan.wang.entity.TickerHourEthUsd;

import java.util.List;


public interface TickerHourService {

    void add(TickerHourBtcUsd tickerHour);
    void add(TickerHourEthUsd tickerHour);
    List<TickerHourBtcUsd> listTickerHour();
    String getValueForTest();

}
