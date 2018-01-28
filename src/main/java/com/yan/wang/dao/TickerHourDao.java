package com.yan.wang.dao;

import com.yan.wang.entity.TickerHourBtcUsd;
import com.yan.wang.entity.TickerHourEthUsd;

import java.util.List;

public interface TickerHourDao {

    void add(TickerHourBtcUsd tickerHour);
    void add(TickerHourEthUsd tickerHour);
    List<TickerHourBtcUsd> listTickerHour();
    String getValueForTest();
}
