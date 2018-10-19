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
 * 订单记录
 * @author Billing
 * @date 2018/8/13 9:31
 */
@TableName("t_order_record")
public class OrderRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    public OrderRecord() {
    }

    public OrderRecord(Integer orderId, Integer orderStatus, Date createTime) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
    }

    /**
     * 订单记录编号
     */
    @TableId
    private Integer orderRecordId;
    /**
     * 订单编号
     */

    private Integer orderId;

    /**
     * 订单状态 0:待支付 1:待支付关闭 2:已付款，待发货 ,3:订单关闭,4:待收货 5:已完成订单
     */
    private Integer orderStatus;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date createTime;

    /**
     * 订单单号
     */
    @TableField(exist = false)
    private String orderNo;

    /**
     * 订单类型  1:矿机产品 2:运算力产品 3:理财产品
     */
    @TableField(exist = false)
    private Integer orderType;

    /**
     * 购买数量
     */
    @TableField(exist = false)
    private BigDecimal amount;

    /**
     * 实收款
     */
    @TableField(exist = false)
    private BigDecimal actualReceipts;

    /**
     * 产品名称
     */
    @TableField(exist = false)
    private String productName;

    public Integer getOrderRecordId() {
        return orderRecordId;
    }

    public void setOrderRecordId(Integer orderRecordId) {
        this.orderRecordId = orderRecordId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActualReceipts() {
        return actualReceipts;
    }

    public void setActualReceipts(BigDecimal actualReceipts) {
        this.actualReceipts = actualReceipts;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
