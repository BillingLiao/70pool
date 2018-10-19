package com.ant.admin.dao;

import com.ant.entity.Income;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:20
 */
public interface IncomeDao extends BaseMapper<Income> {

    /**
     * 插入理财产品收益
     */
    public void insertFinancialIncomeList();

    /**
     * 插入云算力产品收益
     */
    public void insertCloudIncomeList();

    /**
     * 查询出云算力产品所有昨日收益
     * @return
     */
    public List<Income> selectCloudIncomeList();
}
