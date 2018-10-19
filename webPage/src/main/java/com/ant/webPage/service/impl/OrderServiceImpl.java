package com.ant.webPage.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.ant.entity.*;
import com.ant.webPage.dao.*;
import com.ant.webPage.model.UserFinancial;
import com.ant.webPage.service.OrderService;
import com.ant.webPage.util.Result;
import com.ant.webPage.util.SerialNumberUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderRecordDao orderRecordDao;

    @Autowired
    private ProductDao productDao;
    @Autowired
    private MinerProductDao minerProductDao;
    @Autowired
    private CloudProductDao cloudProductDao;
    @Autowired
    private FinancialProductDao financialProductDao;

    /**
     * 添加云算力产品订单
     * @param user 用户
     * @param product 产品
     * @param amount  数量
     * @param actualReceipts 实收款
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addCloudOrder(User user, Product product, BigDecimal amount, BigDecimal actualReceipts) {
        CloudProduct cloudProduct = cloudProductDao.selectByProductId(product.getProductId());
        BigDecimal stock = cloudProduct.getStock(); //库存
        int i = stock.compareTo(BigDecimal.ZERO);
        if(i == 0){ //判断有无库存
            return Result.error("产品已售完");
        }else{
            int num = stock.compareTo(amount);
            if(num == -1){
                return Result.error("不能大于库存");
            }
        }
        String orderCode = SerialNumberUtil.toSerialNumber(user.getUserId());
        Integer categoryId =  product.getCategoryId();
        String orderNo;
        Integer orderType;
        if(categoryId == 1){
            orderType = 1;  //设置订单类型
            orderNo = "KJ" + orderCode;  //生成订单号
        }else if(categoryId == 2){
            orderType = 2;
            orderNo = "YSL"+ orderCode;
        }else{
            return Result.error();
        }
        Date createTime = new Date();
        Order order = new Order(orderNo, product.getProductId(), user.getUserId(), orderType,0, amount,actualReceipts,null,createTime, 0);
        orderDao.insert(order);
        OrderRecord orderRecord = new OrderRecord(order.getOrderId(),0,createTime);
        orderRecordDao.insert(orderRecord);
        return Result.ok("下单成功").put("order",order);
    }

    /**
     * 添加理财产品订单
     * @param user 用户
     * @param product 产品
     * @param actualReceipts 实付款（本金）
     * @param maturityIncome 总收益
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addFinancialOrder(User user, Product product, BigDecimal actualReceipts, BigDecimal maturityIncome) {
        BigDecimal money = new BigDecimal("100000000");
        int num = actualReceipts.compareTo(money);
        if(num == 1){
            return Result.error("请填入正确的金额");
        }
        String orderCode = SerialNumberUtil.toSerialNumber(user.getUserId());
        //生成订单号
        String orderNo;
        orderNo = "LC" + orderCode;
        Date createTime = new Date();
        Order order = new Order(orderNo, product.getProductId(), user.getUserId(), 3,0, null,actualReceipts,maturityIncome,createTime, 0);
        orderDao.insert(order);
        OrderRecord orderRecord = new OrderRecord(order.getOrderId(),0,createTime);
        orderRecordDao.insert(orderRecord);
        return Result.ok("下单成功").put("order",order);
    }

    /**
     * 通过用户id 查询当前持有算力
     * @param userId
     * @return
     */
    @Override
    public BigDecimal selectAmountByUser(Integer userId) {
        return orderDao.selectAmountByUser(userId);
    }

    @Override
    public Result selectCloudOrder(Order order) {
        CloudProduct cloudProduct = cloudProductDao.selectByProductId(order.getProductId());
        Map<String, Object> result = new HashMap<>();
        result.put("order",order);
        result.put("cloudProduct",cloudProduct);
        return Result.ok().put("result",result);
    }

    @Override
    public Result selectFinancialOrder(Order order) {
        FinancialProduct financialProduct = financialProductDao.selectByProductId(order.getProductId());
        Map<String, Object> result = new HashMap<>();
        result.put("order",order);
        result.put("financialProduct",financialProduct);
        return Result.ok().put("result",result);
    }

    /**
     * 查询用户 理财产品 赎回天数跟累计收益
     * @param userId
     * @return
     */
    @Override
    public List<UserFinancial> selectUserFinancial(Integer userId) {
        return orderDao.selectUserFinancial(userId);
    }
}
