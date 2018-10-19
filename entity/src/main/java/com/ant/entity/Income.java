package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品收益表
 *
 * @author Billing
 * @date 2018/8/10 18:08
 */
@TableName("t_income")
public class Income implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收益编号
     */
    @TableId
    private Integer incomeId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 收益类别 -根据订单的产品类别判断
     */
    private Integer incomeType;

    /**
     * 整机理论日收益（btc）
     */
    private BigDecimal theoreticalIncome;

    /**
     * 电费(btc)
     */
    private BigDecimal electricityFees;

    /**
     * 纯收益(btc)
     */
    private BigDecimal pureIncome;

    /**
     * 管理费(%)
     */
    private BigDecimal management;

    /**
     * 结算收益(btc)
     */
    private BigDecimal settlementIncome;

    /**
     * 回本周期(天)
     */
    private BigDecimal returnCycle;

    /**
     * 理财 日收益
     */
    private BigDecimal money;

    /**
     * 日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @DateTimeFormat
    private Date createTime;

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Integer incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getTheoreticalIncome() {
        return theoreticalIncome;
    }

    public void setTheoreticalIncome(BigDecimal theoreticalIncome) {
        this.theoreticalIncome = theoreticalIncome;
    }

    public BigDecimal getElectricityFees() {
        return electricityFees;
    }

    public void setElectricityFees(BigDecimal electricityFees) {
        this.electricityFees = electricityFees;
    }

    public BigDecimal getPureIncome() {
        return pureIncome;
    }

    public void setPureIncome(BigDecimal pureIncome) {
        this.pureIncome = pureIncome;
    }

    public BigDecimal getManagement() {
        return management;
    }

    public void setManagement(BigDecimal management) {
        this.management = management;
    }

    public BigDecimal getSettlementIncome() {
        return settlementIncome;
    }

    public void setSettlementIncome(BigDecimal settlementIncome) {
        this.settlementIncome = settlementIncome;
    }

    public BigDecimal getReturnCycle() {
        return returnCycle;
    }

    public void setReturnCycle(BigDecimal returnCycle) {
        this.returnCycle = returnCycle;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
