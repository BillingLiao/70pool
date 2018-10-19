package com.ant.admin.service.impl;

import com.ant.admin.dao.IncomeDao;
import com.ant.admin.dao.UserDao;
import com.ant.admin.service.IncomeService;
import com.ant.entity.Income;

import com.ant.entity.User;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Iterator;
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

    @Autowired private UserDao userDao;

    /**
     *  1.将云算力跟理财产品付款产品的每日收益插入收益表
     *  2.将云算力昨日收益
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public void insertAllIncome() {
        //云算力产品
        incomeDao.insertCloudIncomeList();
        //理财产品
        incomeDao.insertFinancialIncomeList();

        //查询出运算力昨日收益表
        List<Income> incomeList = incomeDao.selectCloudIncomeList();

        /**
         * 遍历所有收益，插入用户余额
         */
        Iterator<Income> iter = incomeList.iterator();
        while(iter.hasNext()){  //执行过程中会执行数据锁定，性能稍差，若在循环过程中要去掉某个元素只能调用iter.remove()方法。
            Income income = iter.next();
            User user = userDao.selectById(income.getUserId());
            BigDecimal settlementIncome =  income.getSettlementIncome(); //每日结算收益
            BigDecimal btc = user.getBtc().add(settlementIncome);
            user.setBtc(btc);
            userDao.updateAllColumnById(user);
        }
    }
}
