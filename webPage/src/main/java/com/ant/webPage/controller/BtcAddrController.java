package com.ant.webPage.controller;

import com.ant.entity.BtcAddr;
import com.ant.entity.User;
import com.ant.webPage.service.BtcAddrService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Billing
 * @date 2018/9/15 17:44
 */
@RestController
@RequestMapping("/btcAddr")
@CrossOrigin
public class BtcAddrController {

    @Autowired
    private BtcAddrService btcAddrService;

    @RequestMapping("/add")
    public Result add(@SessionAttribute User user, String addr){
            return btcAddrService.addBtcAddr(user,addr);
    }

    @RequestMapping("/update")
    public Result update(@SessionAttribute User user, String addr, @RequestParam Integer btcAddrId){
        return btcAddrService.updateBtcAddr(user,addr,btcAddrId);
    }

    /**
     * 查找有无btcAddrId
     * @return
     */
    @PostMapping("/find")
    public Result find(@SessionAttribute User user){
        Integer userId = user.getUserId();
        BtcAddr btcAddr = btcAddrService.selectBtcAddrByUserId(userId);
        return Result.ok().put("btcAddr", btcAddr);
    }
}
