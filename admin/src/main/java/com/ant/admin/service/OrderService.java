package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.Order;
import com.ant.entity.User;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:30
 */
public interface OrderService extends IService<Order> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     *  判断理财产品是否到期
     *  到期后将本金收益插入用户余额更改订单状态
     */
    void updateTypeByTime();
}
