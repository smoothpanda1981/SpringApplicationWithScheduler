package com.yan.wang.dao;

import com.yan.wang.entity.TickerHourBTCUSD;
import com.yan.wang.entity.TickerHourETHUSD;

import java.util.List;

public interface TickerHourDao {

    void add(TickerHourBTCUSD tickerHour);
    void add(TickerHourETHUSD tickerHour);
    List<TickerHourBTCUSD> listTickerHour();
    String getValueForTest();
}
