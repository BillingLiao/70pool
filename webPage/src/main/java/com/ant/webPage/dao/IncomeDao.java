package com.ant.webPage.dao;

import com.ant.entity.Income;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:20
 */
public interface IncomeDao extends BaseMapper<Income> {

    /**
     * 查询出用户云算力产品昨日所有收益
     * @return
     */
    BigDecimal selectCloudIncomeUser(Integer userId);

    /**
     * 查询出用户云算力产品所有收益
     * @return
     */
    BigDecimal selectCountCloudIncomeUser(Integer userId);

    /**
     * 查询用户理财产品昨日所有收益
     * @return
     */
    BigDecimal selectFinancialIncomeUser(Integer userId);

    /**
     * 查询出用户理财订单的冻结收益
     * @param userId
     * @return
     */
    BigDecimal selectFrozenIncomeUser(Integer userId);

    /**
     * 查询出用户云算力收益记录
     * @param userId
     * @return
     */
    List<Income> selectCloudIncomeList(Integer userId);

    /**
     * 查询出用户理财收益记录
     * @param userId
     * @return
     */
    List<Income> selectFinancialIncomeList(Integer userId);

}
