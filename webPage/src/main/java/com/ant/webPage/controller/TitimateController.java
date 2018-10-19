package com.ant.webPage.controller;

import com.ant.entity.Tstimate;
import com.ant.webPage.service.TstimateService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预估收益controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/tstimate")
@CrossOrigin
public class TitimateController{

    @Autowired
    private TstimateService tstimateService;

    /**
     * 查找最新的一条记录
     * @return
     */
    @GetMapping("/first")
    public Result first(){
        Tstimate tstimateOne =  tstimateService.findOne();
        return Result.ok().put("tstimateOne",tstimateOne);
    }

}
