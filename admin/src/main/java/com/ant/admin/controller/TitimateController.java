package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.common.validator.ValidatorUtils;
import com.ant.admin.service.TstimateService;
import com.ant.entity.Tstimate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 预估收益controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/tstimate")
public class TitimateController extends AbstractController{

    @Autowired
    private TstimateService tstimateService;

    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("tstimate:list")
    public Result list(@RequestParam Map<String,Object> params){
        PageUtils page = tstimateService.queryPage(params);
        return Result.ok().put("page", page);
    }

    /**
     *
     * @param tstimateId
     * @return
     */
    @RequestMapping("/info/{tstimateId}")
    @RequiresPermissions("tstimate:info")
    public Result info(@PathVariable("tstimateId") Integer tstimateId){
        Tstimate tstimate =  tstimateService.infoTstimate(tstimateId);
        return Result.ok().put("tstimate",tstimate);
    }
    /**
     * 保存
     * @param tstimate
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("tstimate:save")
    public Result save(@RequestBody(required = false) Tstimate tstimate){
        ValidatorUtils.validateEntity(tstimate);
        tstimateService.insertTstimate(tstimate);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tstimate:update")
    public Result update(@RequestBody Tstimate tstimate){
        ValidatorUtils.validateEntity(tstimate);
        tstimateService.updateTstimate(tstimate);
        return Result.ok();
    }

    /**
     * 删除
     * @param tstimateIds
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tstimate:delete")
    public Result delete(@RequestBody Integer[] tstimateIds){
        tstimateService.deleteTstimate(tstimateIds);
        return Result.ok("产品已删除");
    }
}
