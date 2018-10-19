package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.Totices;
import com.ant.admin.service.ToticesService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/10 16:50
 */
@RestController
@RequestMapping("/totices")
public class ToticesController {

    @Autowired
    private ToticesService toticesService;

    /**
     * 列表
     * 云算力产品
     * @param params
     * @param totices
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("totices:list")
    public Result list(@RequestParam Map<String,Object> params, Totices totices){
        EntityWrapper<Totices> wrapper = new EntityWrapper<Totices>();
        wrapper.like("totices.totices_title", totices.getToticesTitle());
        PageUtils page = new PageUtils(toticesService.queryPage(params, wrapper));
        return Result.ok().put("page", page);
    }

    /**
     *
     * @param toticesId
     * @return
     */
    @RequestMapping("/info/{toticesId}")
    @RequiresPermissions("totices:info")
    public Result info(@PathVariable("toticesId") Integer toticesId){
        Totices totices =  toticesService.infoTotices(toticesId);
        return Result.ok().put("totices",totices);
    }

    /**
     * 保存
     * @param totices
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("totices:save")
    public Result save(@RequestBody Totices totices){
        ValidatorUtils.validateEntity(totices);
        toticesService.insertTotices(totices);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("totices:update")
    public Result update(@RequestBody Totices totices){
        ValidatorUtils.validateEntity(totices);
        toticesService.updateTotices(totices);
        return Result.ok();
    }

    /**
     * 删除
     * @param toticesId
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("totices:delete")
    public Result delete(@RequestBody Integer[] toticesId){
        toticesService.deleteTotices(toticesId);
        return Result.ok("产品已删除");
    }

}
