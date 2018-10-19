package com.ant.webPage.controller;

import com.ant.entity.Order;
import com.ant.entity.Product;
import com.ant.entity.User;
import com.ant.webPage.model.Profit;
import com.ant.webPage.service.OrderService;
import com.ant.webPage.service.ProductService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 订单controller
 * @author Billing
 * @date 2018/9/11 20:24
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController{

    @Autowired
    private OrderService orderService;

    @Autowired private ProductService productService;


    /**
     *  用户下单
     * @param user  用户
     * @param productId 产品
     * @param amount    购买数量
     * @param actualReceipts  实收款
     * @param maturityIncome 到期收益
     * @return
     */
    @RequestMapping("/add")
    public Result add(@SessionAttribute User user, @RequestParam Integer productId, @RequestParam BigDecimal actualReceipts,
                      @RequestParam(required = false) BigDecimal amount, @RequestParam(required = false) BigDecimal maturityIncome){
        Product product = productService.selectById(productId);
        Integer type = product.getCategoryId();
        if(type == 2){ //云算力产品
            return orderService.addCloudOrder(user,product,amount,actualReceipts);
        }else if(type == 3) { //理财产品
            return orderService.addFinancialOrder(user, product, actualReceipts, maturityIncome);
        }
        return Result.ok();
    }

    /**
     * 根据id查找订单及产品信息
     * @param orderId
     * @return
     */
    @PostMapping("/findOne")
    public Result findOrder(@SessionAttribute User user,@RequestParam Integer orderId){
        Order order = orderService.selectById(orderId);
        if(order.getOrderType() == 2){
            return orderService.selectCloudOrder(order);
        }else if(order.getOrderType() == 3){
            return orderService.selectFinancialOrder(order);
        }
        return Result.ok();
    }

    /**
     * 计算理财产品每日收益，每月收益，到期收益
     * @param actualReceipts 实付款
     * @param rewardRate 年化
     * @param cycle 周期
     * @return
     */
    @PostMapping("/profit")
    public Result profit(@RequestParam BigDecimal actualReceipts,@RequestParam BigDecimal rewardRate,@RequestParam BigDecimal cycle){
        Profit profit = new Profit();
        BigDecimal daily = actualReceipts.multiply(rewardRate).divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)
                .divide(new BigDecimal(360), 4, BigDecimal.ROUND_HALF_UP);
        BigDecimal monthly = daily.multiply(new BigDecimal(30));
        BigDecimal maturityIncome = daily.multiply(cycle);
        profit.setMaturityIncome(maturityIncome);
        profit.setDaily(daily);
        profit.setMonthly(monthly);
        return Result.ok().put("profit",profit);
    }

    /**
     * 计算云算力产品付款金额
     * @param amount 购买数量
     * @param price 单件售价
     * @return
     */
    @PostMapping("/actualReceipts")
    public Result profit(@RequestParam BigDecimal amount,@RequestParam BigDecimal price){
        BigDecimal actualReceipts = amount.multiply(price);
        return Result.ok().put("actualReceipts",actualReceipts);
    }
}
