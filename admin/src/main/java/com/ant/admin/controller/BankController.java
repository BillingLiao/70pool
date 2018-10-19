package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.Bank;
import com.ant.admin.service.BankService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 银行卡服务表
 *
 * @author Billing
 * @date 2018/8/14 18:21
 */
@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    public Result list(@RequestParam Map<String,Object> params){
        PageUtils page=bankService.queryPage(params);
        return Result.ok().put("page",page);
    }

    /**
     * 单条查询
     * @param bankId
     * @return
     */
    @RequestMapping("/info/{bankId}")
    public Result info(@PathVariable("bankId") Integer bankId){
        Bank bank = bankService.selectById(bankId);
        return Result.ok().put("bank",bank);
    }

    /**
     * 保存
     * @param bank
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Bank bank){
        ValidatorUtils.validateEntity(bank);
        bankService.insert(bank);
        return Result.ok();
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody Bank bank){
        ValidatorUtils.validateEntity(bank);
        bankService.updateAllColumnById(bank);

        return Result.ok();
    }

    /**
     * 删除
     * @param bankIds
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result delete(@RequestBody Integer[] bankIds){
        bankService.deleteBatchIds(Arrays.asList(bankIds));
        return Result.ok();
    }

}
