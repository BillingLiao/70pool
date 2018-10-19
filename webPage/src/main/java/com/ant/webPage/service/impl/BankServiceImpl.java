package com.ant.webPage.service.impl;

import com.ant.entity.Bank;
import com.ant.entity.User;
import com.ant.webPage.dao.BankDao;
import com.ant.webPage.service.BankService;
import com.ant.webPage.tool.CheckTool;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("bankService")
public class BankServiceImpl extends ServiceImpl<BankDao, Bank> implements BankService {

    @Autowired
    private BankDao bankDao;

    @Override
    public Result addBank(User user, String cardNumber, String openingBank, String trueName) {
        if(!CheckTool.isString(cardNumber)){
            return Result.error("银行卡号不能为空");
        }
        Bank bank = new Bank();
        bank.setUserId(user.getUserId());
        bank.setCardNumber(cardNumber);
        bank.setOpeningBank(openingBank);
        bank.setTrueName(trueName);
        bank.setCreateAt(new Date());
        bankDao.insert(bank);
        return Result.ok("添加成功").put("bank",bank);
    }

    @Override
    public Result updateBank(User user, String cardNumber, String openingBank, String trueName, Integer bankId) {
        if(!CheckTool.isString(cardNumber)){
            return Result.error("银行卡号不能为空");
        }
        Bank bank = bankDao.selectById(bankId);
        if(bank == null){
            return Result.error("修改的信息不存在");
        }
        Bank bankUpdate = new Bank();
        bankUpdate.setUserId(user.getUserId());
        bankUpdate.setBankId(bankId);
        bankUpdate.setCardNumber(cardNumber);
        bankUpdate.setOpeningBank(openingBank);
        bankUpdate.setTrueName(trueName);
        bankDao.updateAllColumnById(bankUpdate);
        return Result.ok("修改成功").put("bank",bank);
    }

    @Override
    public Bank selectBank(Integer userId) {
        return bankDao.selectBank(userId);
    }
}
