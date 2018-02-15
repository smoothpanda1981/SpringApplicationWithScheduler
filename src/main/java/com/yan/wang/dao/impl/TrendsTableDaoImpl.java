package com.yan.wang.dao.impl;

import com.yan.wang.dao.TickerHourDao;
import com.yan.wang.dao.TrendsTableDao;
import com.yan.wang.entity.TickerHour;
import com.yan.wang.entity.TrendsTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class TrendsTableDaoImpl implements TrendsTableDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(TrendsTable trendsTable) {
        Session session = sessionFactory.openSession();
        session.save(trendsTable);
        session.close();
    }

    @Override
    public List<TrendsTable> listTrendsTable() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TrendsTable> criteria = builder.createQuery(TrendsTable.class);
        criteria.from(TrendsTable.class);

        List<TrendsTable> trendsTablesList = session.createQuery(criteria).getResultList();

        session.close();

        return trendsTablesList;
    }
}
