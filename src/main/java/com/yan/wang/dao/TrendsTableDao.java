package com.yan.wang.dao;

import com.yan.wang.entity.TickerHour;
import com.yan.wang.entity.TrendsTable;

import java.util.List;

public interface TrendsTableDao {

    void add(TrendsTable trendsTable);
    List<TrendsTable> listTrendsTable();
}
