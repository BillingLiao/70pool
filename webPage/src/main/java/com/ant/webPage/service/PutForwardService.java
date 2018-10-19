package com.ant.webPage.service;

import com.ant.entity.PutForward;
import com.ant.entity.User;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;

/**
 * @author Billing
 * @date 2018/9/15 14:45
 */
public interface PutForwardService extends IService<PutForward> {

    Result addBtcPutForward(User user,Integer forwardType, BigDecimal btcTrue);

    Result addCnyPutForward(User user, Integer forwardType,BigDecimal cny);
}
