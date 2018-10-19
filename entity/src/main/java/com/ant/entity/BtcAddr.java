package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 比特币钱包地址
 *
 * @author Billing
 * @date 2018-8-10 16:25
 */
@TableName("t_btc_addr")
public class BtcAddr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 比特币钱包地址编号
     */
    @TableId
    private Integer btcAddrId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 比特币地址
     */
    private String addr;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(fill = FieldFill.INSERT)
    private Date createAt;

    public Integer getBtcAddrId() {
        return btcAddrId;
    }

    public void setBtcAddrId(Integer btcAddrId) {
        this.btcAddrId = btcAddrId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
