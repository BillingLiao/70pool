package com.ant.webPage.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户 拥有的理财产品和收益
 * @author Billing
 * @date 2018/9/14 9:59
 */
public class UserFinancial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 赎回天数
     */
    private BigDecimal redemptionDay;


    /**
     * 本金
     */
    private BigDecimal money;

    /**
     * 累计收益
     */
    private BigDecimal cumulativeIncome;


    public UserFinancial() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setRedemptionDay(BigDecimal redemptionDay) {
        this.redemptionDay = redemptionDay;
    }

    public BigDecimal getRedemptionDay() {
        return redemptionDay;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getCumulativeIncome() {
        return cumulativeIncome;
    }

    public void setCumulativeIncome(BigDecimal cumulativeIncome) {
        this.cumulativeIncome = cumulativeIncome;
    }
}
