package com.ant.admin.service;

import com.ant.entity.Income;
import com.baomidou.mybatisplus.service.IService;

/**
 * 产品收益表
 *
 * @author Billing
 * @date 2018/8/13 19:28
 */
public interface IncomeService extends IService<Income> {

    /**
     * 计算购买产品每日收益，全部插入收益表
     * 将云算力产品每日收益插入用户余额
     */
    public void insertAllIncome();
}
