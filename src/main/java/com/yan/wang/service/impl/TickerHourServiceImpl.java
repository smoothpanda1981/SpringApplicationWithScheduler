package com.yan.wang.service.impl;

import com.yan.wang.dao.TickerHourDao;
import com.yan.wang.entity.TickerHourBtcUsd;
import com.yan.wang.entity.TickerHourEthUsd;
import com.yan.wang.service.TickerHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TickerHourServiceImpl implements TickerHourService {

    @Autowired
    private TickerHourDao tickerHourDao;


    @Transactional
    @Override
    public void add(TickerHourBtcUsd tickerHour) {
        tickerHourDao.add(tickerHour);
    }

    @Transactional
    @Override
    public void add(TickerHourEthUsd tickerHour) {
        tickerHourDao.add(tickerHour);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TickerHourBtcUsd> listTickerHour() {
        return null;
    }

    @Override
    public String getValueForTest() {
        System.out.println("on arrive");
        if (tickerHourDao == null) {
            return "zut";
        } else {
            return tickerHourDao.getValueForTest();
        }
    }

}
