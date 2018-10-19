package com.ant.admin.dao;


import com.ant.entity.Bank;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 银行卡表
 *
 * @author Billing
 * @date 2018/8/13 18:50
 */
public interface BankDao extends BaseMapper<Bank> {
    List<Bank> selectList(Page<Bank> page, @Param("ew") Wrapper<Bank> wrapper);
}
