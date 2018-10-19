package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现记录表
 * @author Billing
 * @date 2018/9/5 18:05
 */
@TableName("t_present_record")
public class PresentRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    public PresentRecord() {
    }

    public PresentRecord(Integer putForwardId, Integer presentStatus, Date createTime) {
        this.putForwardId = putForwardId;
        this.presentStatus = presentStatus;
        this.createTime = createTime;
    }

    /**
     * 提现记录编号
     */
    @TableId
    private Integer presentRecordId;

    /**
     * 提现编号
     */
    private Integer putForwardId;

    /**
     * 提现状态 0:待处理 1:待处理关闭(取消提现) 2:提现失败 3:提现成功
     */
    private Integer presentStatus;

    /**
     * 记录日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date createTime;

    /**
     * 提现单号
     */
    @TableField(exist = false)
    private String forwardNo;

    /**
     * 提现类型  1:矿机产品 2:运算力产品 3:理财产品
     */
    @TableField(exist = false)
    private Integer forwardType;

    /**
     * 提现比特币(云算力)
     */
    @TableField(exist = false)
    private BigDecimal btc;

    /**
     * 提现比特币(扣除手续费)
     */
    @TableField(exist = false)
    private BigDecimal btcTrue;

    /**
     * 提现人民币(理财)
     */
    @TableField(exist = false)
    private BigDecimal cny;

    public Integer getPresentRecordId() {
        return presentRecordId;
    }

    public void setPresentRecordId(Integer presentRecordId) {
        this.presentRecordId = presentRecordId;
    }

    public Integer getPutForwardId() {
        return putForwardId;
    }

    public void setPutForwardId(Integer putForwardId) {
        this.putForwardId = putForwardId;
    }

    public Integer getPresentStatus() {
        return presentStatus;
    }

    public void setPresentStatus(Integer presentStatus) {
        this.presentStatus = presentStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getForwardNo() {
        return forwardNo;
    }

    public void setForwardNo(String forwardNo) {
        this.forwardNo = forwardNo;
    }

    public Integer getForwardType() {
        return forwardType;
    }

    public void setForwardType(Integer forwardType) {
        this.forwardType = forwardType;
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
}
