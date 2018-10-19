package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.service.CurrencyPriceService;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.CurrencyPrice;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 比特币价格controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/currencyPrice")
public class CurrencyPriceController extends AbstractController{

    @Autowired
    private CurrencyPriceService currencyPriceService;

    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("currencyPrice:list")
    public Result list(@RequestParam Map<String,Object> params){
        PageUtils page = currencyPriceService.queryPage(params);
        return Result.ok().put("page", page);
    }

    /**
     *
     * @param priceId
     * @return
     */
    @RequestMapping("/info/{priceId}")
    @RequiresPermissions("currencyPrice:info")
    public Result info(@PathVariable("priceId") Integer priceId){
        CurrencyPrice currencyPrice =  currencyPriceService.infoCurrencyPrice(priceId);
        return Result.ok().put("currencyPrice",currencyPrice);
    }
    /**
     * 保存
     * @param currencyPrice
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("currencyPrice:save")
    public Result save(@RequestBody(required = false) CurrencyPrice currencyPrice){
        ValidatorUtils.validateEntity(currencyPrice);
        if(currencyPrice.getBtcCny() == null){
            return Result.error("数据不能为空");
        }
        currencyPriceService.insertCurrencyPrice(currencyPrice);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("currencyPrice:update")
    public Result update(@RequestBody CurrencyPrice currencyPrice){
        ValidatorUtils.validateEntity(currencyPrice);
        currencyPriceService.updateCurrencyPrice(currencyPrice);
        return Result.ok();
    }

    /**
     * 删除
     * @param priceIds
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("currencyPrice:delete")
    public Result delete(@RequestBody Integer[] priceIds){
        currencyPriceService.deleteCurrencyPrice(priceIds);
        return Result.ok("已删除");
    }
}
