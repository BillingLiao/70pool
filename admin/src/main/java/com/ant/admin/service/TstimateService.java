package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.Tstimate;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface TstimateService extends IService<Tstimate> {

    Tstimate infoTstimate(Integer tstimateId);

    void insertTstimate(Tstimate tstimate);

    void updateTstimate(Tstimate tstimate);

    void deleteTstimate(Integer[] productIds);

    PageUtils queryPage(Map<String, Object> params);
}
