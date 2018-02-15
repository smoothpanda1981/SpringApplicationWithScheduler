package com.yan.wang.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRENDS_TABLE")
public class TrendsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CURRENT_BTC_VALUE")
    private Double currentBtcValue;

    @Column(name = "CURRENT_BTC_VALUE_MINUS_ONE")
    private Double currentBtcValueMinusOne;

    @Column(name = "CURRENT_BTC_VALUE_MINUS_TWO")
    private Double currentBtcValueMinusTwo;

    @Column(name = "CURRENT_BTC_VALUE_MINUS_THREE")
    private Double currentBtcValueMinusThree;

    @Column(name = "CURRENT_BTC_VOLUME")
    private Double currentBtcVolume;

    @Column(name = "CURRENT_BTC_VOLUME_MINUS_ONE")
    private Double currentBtcVolumeMinusOne;

    @Column(name = "CURRENT_BTC_VOLUME_MINUS_TWO")
    private Double currentBtcVolumeMinusTwo;

    @Column(name = "CURRENT_BTC_VOLUME_MINUS_THREE")
    private Double currentBtcVolumeMinusThree;


    @Column(name = "TREND_BTC_VALUE")
    private Integer trendBtcValue;

    @Column(name = "TREND_BTC_VOLUME")
    private Integer trendBtcVolume;

    @Column(name = "TREND_BTC_VALUE_VOLUME_COMBINED")
    private Integer trendBtcValueVolumeCombined;


    @Column(name = "CURRENT_ETH_VALUE")
    private Double currentEthValue;

    @Column(name = "CURRENT_ETH_VALUE_MINUS_ONE")
    private Double currentEthValueMinusOne;

    @Column(name = "CURRENT_ETH_VALUE_MINUS_TWO")
    private Double currentEthValueMinusTwo;

    @Column(name = "CURRENT_ETH_VALUE_MINUS_THREE")
    private Double currentEthValueMinusThree;

    @Column(name = "CURRENT_ETH_VOLUME")
    private Double currentEthVolume;

    @Column(name = "CURRENT_ETH_VOLUME_MINUS_ONE")
    private Double currentEthVolumeMinusOne;

    @Column(name = "CURRENT_ETH_VOLUME_MINUS_TWO")
    private Double currentEthVolumeMinusTwo;

    @Column(name = "CURRENT_ETH_VOLUME_MINUS_THREE")
    private Double currentEthVolumeMinusThree;


    @Column(name = "TREND_ETH_VALUE")
    private Integer trendEthValue;

    @Column(name = "TREND_ETH_VOLUME")
    private Integer trendEthVolume;


    @Column(name = "TREND_ETH_VALUE_VOLUME_COMBINED")
    private Integer trendEthValueVolumeCombined;


    @Column(name = "TREND_BTC_ETH_ALL_COMPARED")
    private Integer trendBtcEthAllCompared;


    @Column(name = "EXECUTION_DATE")
    private String executionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCurrentBtcValue() {
        return currentBtcValue;
    }

    public void setCurrentBtcValue(Double currentBtcValue) {
        this.currentBtcValue = currentBtcValue;
    }

    public Double getCurrentBtcValueMinusOne() {
        return currentBtcValueMinusOne;
    }

    public void setCurrentBtcValueMinusOne(Double currentBtcValueMinusOne) {
        this.currentBtcValueMinusOne = currentBtcValueMinusOne;
    }

    public Double getCurrentBtcValueMinusTwo() {
        return currentBtcValueMinusTwo;
    }

    public void setCurrentBtcValueMinusTwo(Double currentBtcValueMinusTwo) {
        this.currentBtcValueMinusTwo = currentBtcValueMinusTwo;
    }

    public Double getCurrentBtcValueMinusThree() {
        return currentBtcValueMinusThree;
    }

    public void setCurrentBtcValueMinusThree(Double currentBtcValueMinusThree) {
        this.currentBtcValueMinusThree = currentBtcValueMinusThree;
    }

    public Double getCurrentBtcVolume() {
        return currentBtcVolume;
    }

    public void setCurrentBtcVolume(Double currentBtcVolume) {
        this.currentBtcVolume = currentBtcVolume;
    }

    public Double getCurrentBtcVolumeMinusOne() {
        return currentBtcVolumeMinusOne;
    }

    public void setCurrentBtcVolumeMinusOne(Double currentBtcVolumeMinusOne) {
        this.currentBtcVolumeMinusOne = currentBtcVolumeMinusOne;
    }

    public Double getCurrentBtcVolumeMinusTwo() {
        return currentBtcVolumeMinusTwo;
    }

    public void setCurrentBtcVolumeMinusTwo(Double currentBtcVolumeMinusTwo) {
        this.currentBtcVolumeMinusTwo = currentBtcVolumeMinusTwo;
    }

    public Double getCurrentBtcVolumeMinusThree() {
        return currentBtcVolumeMinusThree;
    }

    public void setCurrentBtcVolumeMinusThree(Double currentBtcVolumeMinusThree) {
        this.currentBtcVolumeMinusThree = currentBtcVolumeMinusThree;
    }

    public Double getCurrentEthValue() {
        return currentEthValue;
    }

    public void setCurrentEthValue(Double currentEthValue) {
        this.currentEthValue = currentEthValue;
    }

    public Double getCurrentEthValueMinusOne() {
        return currentEthValueMinusOne;
    }

    public void setCurrentEthValueMinusOne(Double currentEthValueMinusOne) {
        this.currentEthValueMinusOne = currentEthValueMinusOne;
    }

    public Double getCurrentEthValueMinusTwo() {
        return currentEthValueMinusTwo;
    }

    public void setCurrentEthValueMinusTwo(Double currentEthValueMinusTwo) {
        this.currentEthValueMinusTwo = currentEthValueMinusTwo;
    }

    public Double getCurrentEthValueMinusThree() {
        return currentEthValueMinusThree;
    }

    public void setCurrentEthValueMinusThree(Double currentEthValueMinusThree) {
        this.currentEthValueMinusThree = currentEthValueMinusThree;
    }

    public Double getCurrentEthVolume() {
        return currentEthVolume;
    }

    public void setCurrentEthVolume(Double currentEthVolume) {
        this.currentEthVolume = currentEthVolume;
    }

    public Double getCurrentEthVolumeMinusOne() {
        return currentEthVolumeMinusOne;
    }

    public void setCurrentEthVolumeMinusOne(Double currentEthVolumeMinusOne) {
        this.currentEthVolumeMinusOne = currentEthVolumeMinusOne;
    }

    public Double getCurrentEthVolumeMinusTwo() {
        return currentEthVolumeMinusTwo;
    }

    public void setCurrentEthVolumeMinusTwo(Double currentEthVolumeMinusTwo) {
        this.currentEthVolumeMinusTwo = currentEthVolumeMinusTwo;
    }

    public Double getCurrentEthVolumeMinusThree() {
        return currentEthVolumeMinusThree;
    }

    public void setCurrentEthVolumeMinusThree(Double currentEthVolumeMinusThree) {
        this.currentEthVolumeMinusThree = currentEthVolumeMinusThree;
    }

    public Integer getTrendBtcEthAllCompared() {
        return trendBtcEthAllCompared;
    }

    public void setTrendBtcEthAllCompared(Integer trendBtcEthAllCompared) {
        this.trendBtcEthAllCompared = trendBtcEthAllCompared;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public Integer getTrendBtcValue() {
        return trendBtcValue;
    }

    public void setTrendBtcValue(Integer trendBtcValue) {
        this.trendBtcValue = trendBtcValue;
    }

    public Integer getTrendBtcVolume() {
        return trendBtcVolume;
    }

    public void setTrendBtcVolume(Integer trendBtcVolume) {
        this.trendBtcVolume = trendBtcVolume;
    }

    public Integer getTrendBtcValueVolumeCombined() {
        return trendBtcValueVolumeCombined;
    }

    public void setTrendBtcValueVolumeCombined(Integer trendBtcValueVolumeCombined) {
        this.trendBtcValueVolumeCombined = trendBtcValueVolumeCombined;
    }

    public Integer getTrendEthValue() {
        return trendEthValue;
    }

    public void setTrendEthValue(Integer trendEthValue) {
        this.trendEthValue = trendEthValue;
    }

    public Integer getTrendEthVolume() {
        return trendEthVolume;
    }

    public void setTrendEthVolume(Integer trendEthVolume) {
        this.trendEthVolume = trendEthVolume;
    }

    public Integer getTrendEthValueVolumeCombined() {
        return trendEthValueVolumeCombined;
    }

    public void setTrendEthValueVolumeCombined(Integer trendEthValueVolumeCombined) {
        this.trendEthValueVolumeCombined = trendEthValueVolumeCombined;
    }
}
