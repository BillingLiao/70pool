package com.ant.webPage.controller;

import com.ant.entity.Bank;
import com.ant.entity.User;
import com.ant.webPage.service.BankService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Billing
 * @date 2018/9/15 17:43
 */
@RestController
@RequestMapping("/bank")
@CrossOrigin
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/add")
    public Result add(@SessionAttribute User user, @RequestParam String cardNumber, @RequestParam(required = false) String openingBank, @RequestParam(required = false) String trueName){
            return bankService.addBank(user,cardNumber,openingBank,trueName);
    }

    @PostMapping("/update")
    public Result update(@SessionAttribute User user, @RequestParam String cardNumber, @RequestParam(required = false) String openingBank, @RequestParam(required = false) String trueName, @RequestParam Integer bankId) {
        return bankService.updateBank(user,cardNumber,openingBank,trueName,bankId);
    }
    /**
     * 查找有无bankId
     * @return
     */
    @PostMapping("/find")
    public Result find(@SessionAttribute User user){
        Integer userId = user.getUserId();
        Bank bank = bankService.selectBank(userId);
        return Result.ok().put("bank", bank);
    }
}
