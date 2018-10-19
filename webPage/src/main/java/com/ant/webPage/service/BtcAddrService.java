package com.ant.webPage.service;

import com.ant.entity.BtcAddr;
import com.ant.entity.User;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author Billing
 * @date 2018/9/15 17:55
 */
public interface BtcAddrService extends IService<BtcAddr> {
    Result addBtcAddr(User user, String btcAddr);

    Result updateBtcAddr(User user, String btcAddr, Integer btcAddrId);

    BtcAddr selectBtcAddrByUserId(Integer userId);
}
