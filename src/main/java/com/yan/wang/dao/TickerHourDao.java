package com.yan.wang.dao;

import com.yan.wang.entity.TickerHour;

import java.util.List;

public interface TickerHourDao {

    void add(TickerHour tickerHour);
    List<TickerHour> listTickerHour();
    String getValueForTest();
}
