package com.yan.wang.dao.impl;

import com.yan.wang.dao.TickerHourDao;
import com.yan.wang.entity.TickerHourBtcUsd;
import com.yan.wang.entity.TickerHourEthUsd;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TickerHourDaoImpl implements TickerHourDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(TickerHourBtcUsd tickerHour) {
        Session session = sessionFactory.openSession();
        session.save(tickerHour);
        session.close();
    }

    @Override
    public void add(TickerHourEthUsd tickerHour) {
        Session session = sessionFactory.openSession();
        session.save(tickerHour);
        session.close();
    }

    @Override
    public List<TickerHourBtcUsd> listTickerHour() {
        return null;
    }

    @Override
    public String getValueForTest() {
        return "Hello World";
    }


}
