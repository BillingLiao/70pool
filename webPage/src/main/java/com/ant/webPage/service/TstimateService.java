package com.ant.webPage.service;


import com.ant.entity.Tstimate;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface TstimateService extends IService<Tstimate> {

    Tstimate findOne();
}
