package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.BankDao;
import com.ant.entity.Bank;
import com.ant.admin.service.BankService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:00
 */
@Service("bankService")
public class BankServiceImpl extends ServiceImpl<BankDao,Bank> implements BankService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<Bank> bankWrapper=new EntityWrapper<Bank>();
        Page<Bank> page =new Query<Bank>(params).getPage();
        page.setRecords(baseMapper.selectList(page,bankWrapper));
        PageUtils pageUtil = new PageUtils(page);
        return new PageUtils(page);
    }
}
