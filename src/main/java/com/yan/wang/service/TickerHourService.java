package com.yan.wang.service;

import com.yan.wang.entity.TickerHour;

import java.util.List;

public interface TickerHourService {

    void add(TickerHour tickerHour);
    List<TickerHour> listTickerHour();

}
