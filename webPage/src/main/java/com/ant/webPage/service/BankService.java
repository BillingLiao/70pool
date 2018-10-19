package com.ant.webPage.service;

import com.ant.entity.Bank;
import com.ant.entity.User;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author Billing
 * @date 2018/9/15 18:45
 */
public interface BankService extends IService<Bank> {
    Result addBank(User user, String cardNumber, String openingBank, String trueName);

    Result updateBank(User user, String cardNumber, String openingBank, String trueName,Integer bankId);

    Bank selectBank(Integer userId);
}
