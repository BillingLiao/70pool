package com.ant.webPage.dao;

import com.ant.entity.BtcAddr;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author Billing
 * @date 2018/9/15 17:58
 */
public interface BtcAddrDao extends BaseMapper<BtcAddr> {
    BtcAddr selectBtcAddrByUserId(Integer userId);
}
