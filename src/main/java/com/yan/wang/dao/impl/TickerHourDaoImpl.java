package com.yan.wang.dao.impl;

import com.yan.wang.dao.TickerHourDao;
import com.yan.wang.entity.TickerHour;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class TickerHourDaoImpl implements TickerHourDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(TickerHour tickerHour) {
        Session session = sessionFactory.openSession();
        session.save(tickerHour);
        session.close();
    }

    @Override
    public List<TickerHour> listTickerHour() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TickerHour> criteria = builder.createQuery(TickerHour.class);
        criteria.from(TickerHour.class);

        List<TickerHour> tickerHourList = session.createQuery(criteria).getResultList();

        session.close();

        return tickerHourList;
    }

    @Override
    public String getValueForTest() {
        return "Hello World";
    }


}
