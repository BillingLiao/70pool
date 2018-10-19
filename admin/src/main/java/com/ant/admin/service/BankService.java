package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.Bank;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * 银行卡表
 *
 * @author Billing
 * @date 2018/8/13 19:02
 */
public interface BankService extends IService<Bank>{

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
