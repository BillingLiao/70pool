package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 代理表
 *
 * @author Billing
 * @date 2018/8/13 9:36
 */
@TableName("t_proxy")
public class Proxy implements Serializable {

    private static final long serialVersionUID = 1L;

    public Proxy(){

    }

    /**
     * 代理编号
     */
    @TableId
    private Integer proxyId;

    /**
     * 代理人编号
     */
    private Integer userId;

    /**
     * 父代理人编号
     */
    private Integer fatherId;

    /**
     * 子代理人编号
     */
    private Integer sonId;

    /**
     * 代理等级
     */
    private Integer level;

    public Integer getProxyId() {
        return proxyId;
    }

    public void setProxyId(Integer proxyId) {
        this.proxyId = proxyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getSonId() {
        return sonId;
    }

    public void setSonId(Integer sonId) {
        this.sonId = sonId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
