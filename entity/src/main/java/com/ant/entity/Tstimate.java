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
 * @date 2018/9/5 17:17
 */
@TableName("t_tstimate")
public class Tstimate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private Integer tstimateId;

    /**
     *  预估收益
     */
    private BigDecimal tstimate;


    /**
     * 创建日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date createTime;

    public Integer getTstimateId() {
        return tstimateId;
    }

    public void setTstimateId(Integer tstimateId) {
        this.tstimateId = tstimateId;
    }

    public BigDecimal getTstimate() {
        return tstimate;
    }

    public void setTstimate(BigDecimal tstimate) {
        this.tstimate = tstimate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
