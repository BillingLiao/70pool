package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告表
 *
 * @author Billing
 * @date 2018-8-10 16:25
 */
@TableName("t_totices")
public class Totices implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 公告编号
     */
    @TableId
    private Integer toticesId;


    /**
     * 标题
     */
    private String toticesTitle;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    /**
     * 更新日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 发布人
     */
    private Integer publishUser;

    /**
     * 更新人
     */

    private Integer updateUser;

    /**
     * 管理员姓名
     */
    @TableField(exist = false)
    private String userName;

    public Integer getToticesId() {
        return toticesId;
    }

    public void setToticesId(Integer toticesId) {
        this.toticesId = toticesId;
    }

    public String getToticesTitle() {
        return toticesTitle;
    }

    public void setToticesTitle(String toticesTitle) {
        this.toticesTitle = toticesTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(Integer publishUser) {
        this.publishUser = publishUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
