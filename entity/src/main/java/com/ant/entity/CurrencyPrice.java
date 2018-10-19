package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Billing
 * @date 2018/9/5 17:51
 */
@TableName("t_currency_price")
public class CurrencyPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private Integer priceId;

    /**
     * 比特币:人民币价格
     */
    private BigDecimal btcCny;

    /**
     * 比特币:usd价格
     */
    private BigDecimal btcUsd;

    /**
     * 创建日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date createTime;

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public BigDecimal getBtcCny() {
        return btcCny;
    }

    public void setBtcCny(BigDecimal btcCny) {
        this.btcCny = btcCny;
    }

    public BigDecimal getBtcUsd() {
        return btcUsd;
    }

    public void setBtcUsd(BigDecimal btcUsd) {
        this.btcUsd = btcUsd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
