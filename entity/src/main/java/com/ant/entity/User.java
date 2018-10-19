package com.ant.entity;

import com.ant.common.validator.group.AddGroup;
import com.ant.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表
 * @author Billing
 * @date 2018/8/13 10:04
 */
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 手机号码
     */
    @NotBlank(message="手机号码不能为空", groups = AddGroup.class)
    private String telphone;

    /**
     * 登陆密码
     */
    @NotBlank(message="密码不能为空", groups = AddGroup.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 支付宝账号
     */
    private String Alipay;

    /**
     * 云算力余额
     */
    private BigDecimal btc;

    /**
     * 理财余额
     */
    private BigDecimal cny;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 注册时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public User(){

    }

    public User(@NotBlank(message = "手机号码不能为空", groups = AddGroup.class) String telphone, @NotBlank(message = "密码不能为空", groups = AddGroup.class) String password, String salt, BigDecimal btc, BigDecimal cny, Integer status, Date registerTime) {
        this.telphone = telphone;
        this.password = password;
        this.salt = salt;
        this.btc = btc;
        this.cny = cny;
        this.status = status;
        this.registerTime = registerTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlipay() {
        return Alipay;
    }

    public void setAlipay(String alipay) {
        Alipay = alipay;
    }

    public BigDecimal getBtc() {
        return btc;
    }

    public void setBtc(BigDecimal btc) {
        this.btc = btc;
    }

    public BigDecimal getCny() {
        return cny;
    }

    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
