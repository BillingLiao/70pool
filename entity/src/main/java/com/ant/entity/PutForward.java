package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现表
 * @author Billing
 * @date 2018/9/5 18:05
 */
@TableName("t_put_forward")
public class PutForward implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private Integer putForwardId;

    /**
     * 提现单号
     */
    private String forwardNo;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 提现类型  1:矿机产品 2:运算力产品 3:理财产品
     */
    private Integer forwardType;

    /**
     * 提现状态 0:待处理 1:待处理关闭(取消提现) 2:提现失败 3:提现成功
     */
    private Integer forwardStatus;

    /**
     * 提现比特币(云算力)
     */
    private BigDecimal btc;

    /**
     * 提现比特币(云算力)
     */
    private BigDecimal btcTrue;

    /**
     * 提现人民币(理财)
     */
    private BigDecimal cny;

    /**
     * 提现日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date createTime;

    /**
     * 完成时间(提现成功)
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date completionTime;

    public PutForward(){
    }

    public PutForward(String forwardNo, Integer userId, Integer forwardType, Integer forwardStatus, BigDecimal cny, Date createTime) {
        this.forwardNo = forwardNo;
        this.userId = userId;
        this.forwardType = forwardType;
        this.forwardStatus = forwardStatus;
        this.cny = cny;
        this.createTime = createTime;
    }

    public PutForward(String forwardNo, Integer userId, Integer forwardType, Integer forwardStatus, BigDecimal btc, BigDecimal btcTrue, Date createTime) {
        this.forwardNo = forwardNo;
        this.userId = userId;
        this.forwardType = forwardType;
        this.forwardStatus = forwardStatus;
        this.btc = btc;
        this.btcTrue = btcTrue;
        this.createTime = createTime;
    }

    public Integer getPutForwardId() {
        return putForwardId;
    }

    public void setPutForwardId(Integer putForwardId) {
        this.putForwardId = putForwardId;
    }

    public String getForwardNo() {
        return forwardNo;
    }

    public void setForwardNo(String forwardNo) {
        this.forwardNo = forwardNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getForwardType() {
        return forwardType;
    }

    public void setForwardType(Integer forwardType) {
        this.forwardType = forwardType;
    }

    public Integer getForwardStatus() {
        return forwardStatus;
    }

    public void setForwardStatus(Integer forwardStatus) {
        this.forwardStatus = forwardStatus;
    }

    public BigDecimal getBtc() {
        return btc;
    }

    public void setBtc(BigDecimal btc) {
        this.btc = btc;
    }

    public BigDecimal getBtcTrue() {
        return btcTrue;
    }

    public void setBtcTrue(BigDecimal btcTrue) {
        this.btcTrue = btcTrue;
    }

    public BigDecimal getCny() {
        return cny;
    }

    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }
}
