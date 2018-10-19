package com.ant.webPage.controller;

import com.ant.entity.Totices;
import com.ant.webPage.service.ToticesService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/10 16:50
 */
@RestController
@RequestMapping("/totices")
@CrossOrigin
public class ToticesController {

    @Autowired
    private ToticesService toticesService;

    /**
     * 列表
     * 公告
     * @return
     */
    @GetMapping("/findList")
    public Result findList(){
        List<Totices> toticesList = toticesService.findList();
        return Result.ok().put("toticesList",toticesList);
    }

    /**
     *  查询单条
     * @param toticesId
     * @return
     */
    @RequestMapping("/findOne/{toticesId}")
    public Result info(@PathVariable("toticesId") Integer toticesId){
        Totices totices =  toticesService.selectOneById(toticesId);
        return Result.ok().put("totices",totices);
    }

}
