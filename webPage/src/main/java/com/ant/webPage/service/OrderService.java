package com.ant.webPage.service;

import com.ant.entity.Order;
import com.ant.entity.Product;
import com.ant.entity.User;
import com.ant.webPage.model.UserFinancial;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单service
 * @author Billing
 * @date 2018/9/5 14:55
 */
public interface OrderService extends IService<Order> {

    /**
     * 添加云算力订单
     * @param user
     * @param product
     * @param amount
     * @param actualReceipts
     * @return
     */
    Result addCloudOrder(User user, Product product, BigDecimal amount, BigDecimal actualReceipts);

    /**
     * 添加理财订单
     * @param user
     * @param product
     * @param actualReceipts
     * @param maturityIncome
     * @return
     */
    Result addFinancialOrder(User user, Product product, BigDecimal actualReceipts, BigDecimal maturityIncome);

    /**
     * 通过用户id 查询当前持有算力
     * @param userId
     * @return
     */
    BigDecimal selectAmountByUser(Integer userId);

    /**
     * 查找云算力订单及产品记录
     * @param order
     * @return
     */
    Result selectCloudOrder(Order order);

    /**
     * 查找理财订单及产品记录
     * @param order
     * @return
     */
    Result selectFinancialOrder(Order order);

    /**
     * 查询用户 理财产品 赎回天数跟累计收益
     * @param userId
     * @return
     */
    List<UserFinancial> selectUserFinancial(Integer userId);
}
