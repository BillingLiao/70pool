package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 理财产品明细表
 *
 * @author Billing
 * @date 2018/8/13 10:21
 */
@TableName("t_financial_product")
public class FinancialProduct extends Product implements Serializable {

    private static final long serialVersionUID = 1L;

    public FinancialProduct(){
    }

    /**
     * 理财明细id
     */
    @TableId
    private Integer financialId;

    /**
     * 产品Id
     */
    private Integer productId;

    /**
     * 起投金额
     */
    private BigDecimal thresholdAmount;

    /**
     * 投资步长
     */
    private BigDecimal stepTerm;

    /**
     * 锁定期
     */
    private Integer lockAmount;

    /**
     * 年化收益率
     */
    private BigDecimal rewardRate;


    /**
     * 投资周期
     */
    private BigDecimal cycle;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 假删除 0：未删除 1：删除
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 分类名称
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 产品类别编号
     */
    @TableField(exist = false)
    private Integer categoryId;

    /**
     * 产品名称
     */
    @TableField(exist = false)
    private String productName;

    /**
     * 产品介绍
     */
    @TableField(exist = false)
    private String introduction;


    /**
     * 是否上架：1=上架/0=下架
     */
    @TableField(exist = false)
    private Integer showInShelve;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(exist = false)
    private Date createAt;

    /**
     * 更新时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(exist = false)
    private Date updateAt;

    /**
     * 创建者
     */
    @TableField(exist = false)
    private Integer createUser;

    /**
     * 更新者
     */
    @TableField(exist = false)
    private Integer updateUser;

    public Integer getFinancialId() {
        return financialId;
    }

    public void setFinancialId(Integer financialId) {
        this.financialId = financialId;
    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public BigDecimal getStepTerm() {
        return stepTerm;
    }

    public void setStepTerm(BigDecimal stepTerm) {
        this.stepTerm = stepTerm;
    }

    public Integer getLockAmount() {
        return lockAmount;
    }

    public void setLockAmount(Integer lockAmount) {
        this.lockAmount = lockAmount;
    }

    public BigDecimal getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(BigDecimal rewardRate) {
        this.rewardRate = rewardRate;
    }

    public BigDecimal getCycle() {
        return cycle;
    }

    public void setCycle(BigDecimal cycle) {
        this.cycle = cycle;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public Integer getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public Integer getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String getIntroduction() {
        return introduction;
    }

    @Override
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public Integer getShowInShelve() {
        return showInShelve;
    }

    @Override
    public void setShowInShelve(Integer showInShelve) {
        this.showInShelve = showInShelve;
    }

    @Override
    public Date getCreateAt() {
        return createAt;
    }

    @Override
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public Date getUpdateAt() {
        return updateAt;
    }

    @Override
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public Integer getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    @Override
    public Integer getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "FinancialProduct{" +
                "financialId=" + financialId +
                ", productId=" + productId +
                ", thresholdAmount=" + thresholdAmount +
                ", stepTerm=" + stepTerm +
                ", lockAmount=" + lockAmount +
                ", rewardRate=" + rewardRate +
                ", cycle=" + cycle +
                ", delFlag=" + delFlag +
                ", categoryName='" + categoryName + '\'' +
                ", categoryId=" + categoryId +
                ", productName='" + productName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", showInShelve=" + showInShelve +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                '}';
    }
}
