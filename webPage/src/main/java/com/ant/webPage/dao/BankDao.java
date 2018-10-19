package com.ant.webPage.dao;

import com.ant.entity.Bank;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author Billing
 * @date 2018/9/15 18:48
 */
public interface BankDao extends BaseMapper<Bank> {
    Bank selectBank(Integer userId);
}
