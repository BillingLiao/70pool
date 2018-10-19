package com.ant.webPage.controller;

import com.ant.entity.User;
import com.ant.webPage.service.PutForwardService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 订单controller
 * @author Billing
 * @date 2018/9/11 20:24
 */
@RestController
@RequestMapping("/putForward")
@CrossOrigin
public class PutForwardController {

    @Autowired
    private PutForwardService putForwardService;

    /**
     * 用户提现
     * @param user 用户
     * @param forwardType 提现类型 0：比特币地址钱包;1：银行卡
     * @param btcTrue
     * @param cny
     * @return
     */
    @RequestMapping("/add")
    public Result add(@SessionAttribute User user, @RequestParam Integer forwardType, @RequestParam(required = false) BigDecimal btcTrue, @RequestParam(required = false) BigDecimal cny){
        if(forwardType == 0){
            return putForwardService.addBtcPutForward(user,forwardType,btcTrue);
        }else if(forwardType == 1) {
            return putForwardService.addCnyPutForward(user,forwardType,cny);
        }
        return Result.ok();
    }
}
