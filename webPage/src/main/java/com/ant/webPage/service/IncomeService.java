package com.ant.webPage.service;

import com.ant.entity.Income;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品收益表
 *
 * @author Billing
 * @date 2018/8/13 19:28
 */
public interface IncomeService extends IService<Income> {
    /**
     * 查询出用户云算力产品昨日所有收益
     * @return
     */
    public BigDecimal selectCloudIncomeUser(Integer userId);

    /**
     * 查询出用户理财产品昨日所有收益
     * @return
     */
    public BigDecimal selectFinancialIncomeUser(Integer userId);

    /**
     * 查询出用户云算力收益记录
     * @param userId
     * @return
     */
    public List<Income> selectCloudIncomeList(Integer userId);

    /**
     * 查询出用户理财收益记录
     * @param userId
     * @return
     */
    public List<Income> selectFinancialIncomeList(Integer userId);
}
