package com.yan.wang.service.impl;

import com.yan.wang.dao.TickerHourDao;
import com.yan.wang.entity.TickerHour;
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
    public void add(TickerHour tickerHour) {

    }

    @Transactional(readOnly = true)
    @Override
    public List<TickerHour> listTickerHour() {
        return null;
    }
}
