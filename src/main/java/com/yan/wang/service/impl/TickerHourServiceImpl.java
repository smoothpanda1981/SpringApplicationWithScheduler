package com.yan.wang.service.impl;

import com.yan.wang.dao.TickerHourDao;
import com.yan.wang.dao.TrendsTableDao;
import com.yan.wang.entity.TickerHour;
import com.yan.wang.entity.TrendsTable;
import com.yan.wang.service.TickerHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
public class TickerHourServiceImpl implements TickerHourService {

    @Autowired
    private TickerHourDao tickerHourDao;

    @Autowired
    private TrendsTableDao trendsTableDao;


    @Transactional
    @Override
    public void add(TickerHour tickerHour) {
        tickerHourDao.add(tickerHour);
    }

    @Transactional(readOnly = true)
    @Override
    public void computeAndStoreCurrentTrends() {
        List<TickerHour> tickerHourList = tickerHourDao.listTickerHour();

        if (tickerHourList != null) {
            Comparator<TickerHour> byTickerHourId = (e1, e2) -> Long.compare(e1.getId(), e2.getId());

            List<TickerHour> tickerHourListBtc = (List<TickerHour>) tickerHourList.stream().filter(tickerHour -> "BTC".equals(tickerHour.getCryptoCurrency())).sorted(byTickerHourId);
            List<TickerHour> tickerHourListEth = (List<TickerHour>) tickerHourList.stream().filter(tickerHour -> "ETH".equals(tickerHour.getCryptoCurrency())).sorted(byTickerHourId);


            TrendsTable trendsTable = buildTrendsTable(tickerHourListBtc, tickerHourListEth);
            trendsTableDao.add(trendsTable);
        }
    }

    private TrendsTable buildTrendsTable (List<TickerHour> tickerHourListBtc, List<TickerHour> tickerHourListEth) {
        TrendsTable trendsTable = new TrendsTable();

        trendsTable.setCurrentBtcValue(tickerHourListBtc.get(tickerHourListBtc.size() - 1).getLast());
        trendsTable.setCurrentBtcValueMinusOne(tickerHourListBtc.get(tickerHourListBtc.size() - 2).getLast());
        trendsTable.setCurrentBtcValueMinusTwo(tickerHourListBtc.get(tickerHourListBtc.size() - 3).getLast());
        trendsTable.setCurrentBtcValueMinusThree(tickerHourListBtc.get(tickerHourListBtc.size() - 4).getLast());

        trendsTable.setCurrentBtcVolume(tickerHourListBtc.get(tickerHourListBtc.size() - 1).getVolume());
        trendsTable.setCurrentBtcVolumeMinusOne(tickerHourListBtc.get(tickerHourListBtc.size() - 2).getVolume());
        trendsTable.setCurrentBtcVolumeMinusTwo(tickerHourListBtc.get(tickerHourListBtc.size() - 3).getVolume());
        trendsTable.setCurrentBtcVolumeMinusThree(tickerHourListBtc.get(tickerHourListBtc.size() - 4).getVolume());

        Integer trendBtcValue = computTrendsForValueVolume(trendsTable.getCurrentBtcValueMinusThree(), trendsTable.getCurrentBtcValueMinusTwo(), trendsTable.getCurrentBtcValueMinusOne(), trendsTable.getCurrentBtcValue());
        trendsTable.setTrendBtcValue(trendBtcValue);
        Integer trendBtcVolume = computTrendsForValueVolume(trendsTable.getCurrentBtcVolumeMinusThree(), trendsTable.getCurrentBtcVolumeMinusTwo(), trendsTable.getCurrentBtcVolumeMinusOne(), trendsTable.getCurrentBtcVolume());
        trendsTable.setTrendBtcVolume(trendBtcVolume);
        Integer trendBtcValueVolumeCombined = computTrendsForValueVolumeCombined(trendBtcValue, trendBtcVolume);
        trendsTable.setTrendBtcValueVolumeCombined(trendBtcValueVolumeCombined);


        trendsTable.setCurrentEthValue(tickerHourListEth.get(tickerHourListEth.size() - 1).getLast());
        trendsTable.setCurrentEthValueMinusOne(tickerHourListEth.get(tickerHourListEth.size() - 2).getLast());
        trendsTable.setCurrentEthValueMinusTwo(tickerHourListEth.get(tickerHourListEth.size() - 3).getLast());
        trendsTable.setCurrentEthValueMinusThree(tickerHourListEth.get(tickerHourListEth.size() - 4).getLast());

        trendsTable.setCurrentEthVolume(tickerHourListEth.get(tickerHourListEth.size() - 1).getVolume());
        trendsTable.setCurrentEthVolumeMinusOne(tickerHourListEth.get(tickerHourListEth.size() - 2).getVolume());
        trendsTable.setCurrentEthVolumeMinusTwo(tickerHourListEth.get(tickerHourListEth.size() - 3).getVolume());
        trendsTable.setCurrentEthVolumeMinusThree(tickerHourListEth.get(tickerHourListEth.size() - 4).getVolume());

        Integer trendEthValue = computTrendsForValueVolume(trendsTable.getCurrentEthValueMinusThree(), trendsTable.getCurrentEthValueMinusTwo(), trendsTable.getCurrentEthValueMinusOne(), trendsTable.getCurrentEthValue());
        trendsTable.setTrendEthValue(trendEthValue);
        Integer trendEthVolume = computTrendsForValueVolume(trendsTable.getCurrentBtcVolumeMinusThree(), trendsTable.getCurrentBtcVolumeMinusTwo(), trendsTable.getCurrentBtcVolumeMinusOne(), trendsTable.getCurrentBtcVolume());
        trendsTable.setTrendEthVolume(trendEthVolume);
        Integer trendEthValueVolumeCombined = computTrendsForValueVolumeCombined(trendEthValue, trendEthVolume);
        trendsTable.setTrendEthValueVolumeCombined(trendEthValueVolumeCombined);

        Integer trendBtcEthAllCompared = computTrendsForValueVolumeCombined(trendBtcValueVolumeCombined, trendEthValueVolumeCombined);
        trendsTable.setTrendBtcEthAllCompared(trendBtcEthAllCompared);


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        trendsTable.setExecutionDate(formatDateTime);


        return  trendsTable;
    }

    public Integer computTrendsForValueVolume(Double p1, Double p2, Double p3, Double p4) {
        Integer counter = 0;
        counter = counter + p1.compareTo(p2);
        counter = counter + p2.compareTo(p3);
        counter = counter + p3.compareTo(p4);

        if (counter == 3) {
            return 10;
        } else if (counter == 1) {
            if (p1.compareTo(p4) > 0) {
                return 10;
            } else {
                return -10;
            }
        } else {
            return -10;
        }
    }

    public Integer computTrendsForValueVolumeCombined(Integer trendBtcValue, Integer trendBtcVolume) {
        Integer counter = 0;
        counter = counter + trendBtcValue.compareTo(trendBtcVolume);
        if (counter == 20) {
            return 10;
        } else {
            return -10;
        }
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
