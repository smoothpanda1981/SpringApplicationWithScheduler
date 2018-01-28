package com.yan.wang.service.impl;

import com.yan.wang.dao.TickerHourDao;
import com.yan.wang.entity.TickerHourBTCUSD;
import com.yan.wang.entity.TickerHourETHUSD;
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
    public void add(TickerHourBTCUSD tickerHour) {
        tickerHourDao.add(tickerHour);
    }

    @Transactional
    @Override
    public void add(TickerHourETHUSD tickerHour) {
        tickerHourDao.add(tickerHour);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TickerHourBTCUSD> listTickerHour() {
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
