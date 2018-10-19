package com.ant.webPage.dao;

import com.ant.entity.Tstimate;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author Billing
 * @date 2018/8/13 19:20
 */
public interface TstimateDao extends BaseMapper<Tstimate> {

    /**
     * 查询最新一条记录
     * @return
     */
     Tstimate findFirst();
}
