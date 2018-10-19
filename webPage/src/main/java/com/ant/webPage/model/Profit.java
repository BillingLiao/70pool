package com.ant.webPage.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 计算利息
 * @author Billing
 * @date 2018/9/14 9:59
 */
public class Profit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 每日收益
     */
    private BigDecimal daily;

    /**
     * 每月收益
     */
    private BigDecimal monthly;

    /**
     * 到期收益
     */
    private BigDecimal maturityIncome;

    public Profit() {
    }

    public BigDecimal getDaily() {
        return daily;
    }

    public void setDaily(BigDecimal daily) {
        this.daily = daily;
    }

    public BigDecimal getMonthly() {
        return monthly;
    }

    public void setMonthly(BigDecimal monthly) {
        this.monthly = monthly;
    }

    public BigDecimal getMaturityIncome() {
        return maturityIncome;
    }

    public void setMaturityIncome(BigDecimal maturityIncome) {
        this.maturityIncome = maturityIncome;
    }
}
