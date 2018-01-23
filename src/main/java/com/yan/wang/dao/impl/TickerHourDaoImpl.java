package com.yan.wang.dao.impl;

import com.yan.wang.dao.TickerHourDao;
import com.yan.wang.entity.TickerHour;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TickerHourDaoImpl implements TickerHourDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(TickerHour tickerHour) {

    }

    @Override
    public List<TickerHour> listTickerHour() {
        return null;
    }
}
