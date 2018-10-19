package com.ant.admin.controller;

import com.ant.admin.common.utils.Result;
import com.ant.admin.dao.IncomeDao;
import com.ant.admin.dao.OrderDao;
import com.ant.admin.dao.UserDao;
import com.ant.entity.Income;
import com.ant.entity.Order;
import com.ant.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * 测试controller
 * @author Billing
 * @date 2018/9/14 13:05
 */
@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @Autowired
    private IncomeDao incomeDao;

    @Autowired private UserDao userDao;

    @Autowired private OrderDao orderDao;

    @RequestMapping("/insertAllIncome")
    @Transactional(rollbackFor=Exception.class)
    public Result insertAllIncome(){
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
        return Result.ok("测试成功");
    }


    @RequestMapping("/updateTypeByTime")
    @Transactional(rollbackFor=Exception.class)
    public Result updateTypeByTime(){
        /**
         *  判断理财产品是否到期
         *  将今日到期产品的本金与收益插入用户余额
         */
        List<Order> orderList =  orderDao.selectByTime();
        Iterator<Order> iter = orderList.iterator();
        while(iter.hasNext()){  //执行过程中会执行数据锁定，性能稍差，若在循环过程中要去掉某个元素只能调用iter.remove()方法。
            Order order = iter.next();
            User user = userDao.selectById(order.getUserId());
            BigDecimal actualReceipts =  order.getActualReceipts(); //订单实付款(本金)
            BigDecimal maturityIncome = order.getMaturityIncome(); //到期收益
            BigDecimal cny = user.getCny().add(actualReceipts.add(maturityIncome));
            user.setCny(cny);
            userDao.updateAllColumnById(user);
        }
        /**
         * 到期后更改订单状态
         */
        orderDao.updateTypeByTime();
        return Result.ok("测试成功");
    }

}
