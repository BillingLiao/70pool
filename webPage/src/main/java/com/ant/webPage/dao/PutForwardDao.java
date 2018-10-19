package com.ant.webPage.dao;

import com.ant.entity.PutForward;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.math.BigDecimal;

public interface PutForwardDao extends BaseMapper<PutForward> {

    /**
     * 查找用户btc提现中金额
     * @param userId
     * @return
     */
    BigDecimal selectBtcPresentByUserId(Integer userId);

    /**
     * 查找用户cny提现中金额
     * @param userId
     * @return
     */
    BigDecimal selectCnyPresentByUserId(Integer userId);
}
