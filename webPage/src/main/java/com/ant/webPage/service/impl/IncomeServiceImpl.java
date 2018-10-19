package com.ant.webPage.service.impl;

import com.ant.entity.Income;
import com.ant.webPage.dao.IncomeDao;
import com.ant.webPage.service.IncomeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 收益表service实现类
 * @author Billing
 * @date 2018/8/13 19:36
 */
@Service("incomeService")
public class IncomeServiceImpl extends ServiceImpl<IncomeDao,Income> implements IncomeService {

    @Autowired
    private IncomeDao incomeDao;

    /**
     * 查询出用户云算力产品昨日所有收益
     * @return
     */
    @Override
    public BigDecimal selectCloudIncomeUser(Integer userId) {
        return incomeDao.selectCloudIncomeUser(userId);
    }

    /**
     * 查询出用户理财产品昨日所有收益
     * @return
     */
    @Override
    public BigDecimal selectFinancialIncomeUser(Integer userId) {
        return incomeDao.selectFinancialIncomeUser(userId);
    }

    /**
     * 查询出用户云算力收益记录
     * @param userId
     * @return
     */
    @Override
    public List<Income> selectCloudIncomeList(Integer userId){
        return incomeDao.selectCloudIncomeList(userId);
    }

    /**
     * 查询出用户理财收益记录
     * @param userId
     * @return
     */
    @Override
    public List<Income> selectFinancialIncomeList(Integer userId){
        return incomeDao.selectFinancialIncomeList(userId);
    }
}
