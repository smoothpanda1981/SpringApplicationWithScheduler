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
import java.util.stream.Collectors;

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

            List<TickerHour> tickerHourListBtc = tickerHourList.stream().filter(tickerHour -> "BTC".equals(tickerHour.getCryptoCurrency())).sorted(Comparator.comparing(TickerHour::getId).reversed()).collect(Collectors.toList());
            for (TickerHour tickerHour : tickerHourListBtc) {
                System.out.println(tickerHour.getCryptoCurrency() + " - " + tickerHour.getId());
            }

            List<TickerHour> tickerHourListEth = tickerHourList.stream().filter(tickerHour -> "ETH".equals(tickerHour.getCryptoCurrency())).sorted(Comparator.comparing(TickerHour::getId).reversed()).collect(Collectors.toList());

            for (TickerHour tickerHour : tickerHourListEth) {
                System.out.println(tickerHour.getCryptoCurrency() + " - " + tickerHour.getId());
            }

            TrendsTable trendsTable = buildTrendsTable(tickerHourListBtc, tickerHourListEth);
            trendsTableDao.add(trendsTable);
        }
    }

    private TrendsTable buildTrendsTable (List<TickerHour> tickerHourListBtc, List<TickerHour> tickerHourListEth) {
        TrendsTable trendsTable = new TrendsTable();

        trendsTable.setCurrentBtcValue(tickerHourListBtc.get(0).getLast());
        System.out.println(tickerHourListBtc.get(0).getId() + " : " + tickerHourListBtc.get(0).getLast());
        trendsTable.setCurrentBtcValueMinusOne(tickerHourListBtc.get(1).getLast());
        System.out.println(tickerHourListBtc.get(1).getId() + " : " + tickerHourListBtc.get(1).getLast());
        trendsTable.setCurrentBtcValueMinusTwo(tickerHourListBtc.get(2).getLast());
        System.out.println(tickerHourListBtc.get(2).getId() + " : " + tickerHourListBtc.get(2).getLast());
        trendsTable.setCurrentBtcValueMinusThree(tickerHourListBtc.get(3).getLast());
        System.out.println(tickerHourListBtc.get(3).getId() + " : " + tickerHourListBtc.get(3).getLast());


        trendsTable.setCurrentBtcVolume(tickerHourListBtc.get(0).getVolume());
        trendsTable.setCurrentBtcVolumeMinusOne(tickerHourListBtc.get(1).getVolume());
        trendsTable.setCurrentBtcVolumeMinusTwo(tickerHourListBtc.get(2).getVolume());
        trendsTable.setCurrentBtcVolumeMinusThree(tickerHourListBtc.get(3).getVolume());

        Integer trendBtcValue = computTrendsForValueVolume(trendsTable.getCurrentBtcValueMinusThree(), trendsTable.getCurrentBtcValueMinusTwo(), trendsTable.getCurrentBtcValueMinusOne(), trendsTable.getCurrentBtcValue());
        trendsTable.setTrendBtcValue(trendBtcValue);
        Integer trendBtcVolume = computTrendsForValueVolume(trendsTable.getCurrentBtcVolumeMinusThree(), trendsTable.getCurrentBtcVolumeMinusTwo(), trendsTable.getCurrentBtcVolumeMinusOne(), trendsTable.getCurrentBtcVolume());
        trendsTable.setTrendBtcVolume(trendBtcVolume);
        Integer trendBtcValueVolumeCombined = computeTrendsForValueVolumeCombined(trendBtcValue, trendBtcVolume);
        trendsTable.setTrendBtcValueVolumeCombined(trendBtcValueVolumeCombined);


        trendsTable.setCurrentEthValue(tickerHourListEth.get(0).getLast());
        trendsTable.setCurrentEthValueMinusOne(tickerHourListEth.get(1).getLast());
        trendsTable.setCurrentEthValueMinusTwo(tickerHourListEth.get(2).getLast());
        trendsTable.setCurrentEthValueMinusThree(tickerHourListEth.get(3).getLast());

        trendsTable.setCurrentEthVolume(tickerHourListEth.get(0).getVolume());
        trendsTable.setCurrentEthVolumeMinusOne(tickerHourListEth.get(1).getVolume());
        trendsTable.setCurrentEthVolumeMinusTwo(tickerHourListEth.get(2).getVolume());
        trendsTable.setCurrentEthVolumeMinusThree(tickerHourListEth.get(3).getVolume());

        Integer trendEthValue = computTrendsForValueVolume(trendsTable.getCurrentEthValueMinusThree(), trendsTable.getCurrentEthValueMinusTwo(), trendsTable.getCurrentEthValueMinusOne(), trendsTable.getCurrentEthValue());
        trendsTable.setTrendEthValue(trendEthValue);
        Integer trendEthVolume = computTrendsForValueVolume(trendsTable.getCurrentBtcVolumeMinusThree(), trendsTable.getCurrentBtcVolumeMinusTwo(), trendsTable.getCurrentBtcVolumeMinusOne(), trendsTable.getCurrentBtcVolume());
        trendsTable.setTrendEthVolume(trendEthVolume);
        Integer trendEthValueVolumeCombined = computeTrendsForValueVolumeCombined(trendEthValue, trendEthVolume);
        trendsTable.setTrendEthValueVolumeCombined(trendEthValueVolumeCombined);

        Integer trendBtcEthAllCompared = computeTrendsForValueVolumeCombined(trendBtcValueVolumeCombined, trendEthValueVolumeCombined);
        trendsTable.setTrendBtcEthAllCompared(trendBtcEthAllCompared);


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        trendsTable.setExecutionDate(formatDateTime);


        return  trendsTable;
    }

    public Integer computTrendsForValueVolume(Double p1, Double p2, Double p3, Double p4) {
        System.out.println("p1 : " + p1 + " - p2 : " + p2 + " - p3 : " + p3 + " - p4 : " + p4);
        Integer counter = 0;
        counter = counter + (p1.compareTo(p2));
        counter = counter + p2.compareTo(p3);
        counter = counter + p3.compareTo(p4);

        if (counter == 3) {
            System.out.println("counter = 3 : 10");
            return 10;
        } else if (counter == 1) {
            if (p1.compareTo(p4) > 0) {
                System.out.println("counter = 1 : 10");
                return 10;
            } else {
                System.out.println("counter = 1 : -10");
                return -10;
            }
        } else {
            System.out.println("counter = -3 : -10");
            return -10;
        }
    }

    public Integer computeTrendsForValueVolumeCombined(Integer trendBtcValue, Integer trendBtcVolume) {
        System.out.println("trendBtcValue : " + trendBtcValue + " - trendBtcVolume : " + trendBtcVolume);
        Integer counter = 0;
        counter = trendBtcValue + trendBtcVolume;
        if (counter == 20) {
            System.out.println("counter = " + counter + " - so : 10");
            return 10;
        } else {
            System.out.println("counter = " + counter + " - so : -10");
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
