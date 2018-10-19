package com.ant.webPage.dao;

import com.ant.entity.Order;
import com.ant.webPage.model.UserFinancial;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单dao
 * @author Billing
 * @date 2018/8/13 19:22
 */
public interface OrderDao extends BaseMapper<Order> {

    /**
     * 通过用户id 查询当前持有算力
     * @param userId
     * @return
     */
    BigDecimal selectAmountByUser(Integer userId);

    /**
     * 查询用户 理财产品 赎回天数跟累计收益
     * @param userId
     * @return
     */
    List<UserFinancial> selectUserFinancial(Integer userId);
}
