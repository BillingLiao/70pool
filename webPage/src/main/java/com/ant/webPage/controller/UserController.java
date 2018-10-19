package com.ant.webPage.controller;

import com.ant.entity.Income;
import com.ant.entity.User;
import com.ant.webPage.model.Account;
import com.ant.webPage.model.UserFinancial;
import com.ant.webPage.service.IncomeService;
import com.ant.webPage.service.OrderService;
import com.ant.webPage.service.UserService;
import com.ant.webPage.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/9/5 15:09
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private IncomeService incomeService;

    /**
     *  查询用户账户信息
     * @param user
     * @return
     */
    @RequestMapping("/my")
    public Result my(@SessionAttribute User user){
        Integer userId = user.getUserId();
        Account account = userService.selectAccountByUserId(userId);
        return Result.ok().put("account",account);
    }

    /**
     * 1.查询用户账户信息
     * 2.查询用户理财产品名称，赎回天数跟累计收益
     * @param user
     * @return
     */
    @RequestMapping("/detailed")
    private Result detailed(@SessionAttribute User user){
        Integer userId = user.getUserId();
        Account account = userService.selectAccountByUserId(userId);
        List<UserFinancial> userFinancialList = orderService.selectUserFinancial(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("account",account);
        result.put("userFinancialList",userFinancialList);
        return Result.ok().put("result",result);
    }

    /**
     * 1.查询用户账户信息
     * 2.分类别查询收益记录
     * @param user
     * @return
     */
    @RequestMapping("/revenueRecord")
    public Result revenueRecord(@SessionAttribute User user){
        Integer userId = user.getUserId();
        Account account = userService.selectAccountByUserId(userId);
        List<Income> btcIncomelist = incomeService.selectCloudIncomeList(userId);
        List<Income> cnyIncomelist = incomeService.selectFinancialIncomeList(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("account",account);
        result.put("btcIncomelist",btcIncomelist);
        result.put("cnyIncomelist",cnyIncomelist);
        return Result.ok().put("result",result);
    }

}
