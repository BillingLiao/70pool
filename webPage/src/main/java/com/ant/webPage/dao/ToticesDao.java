package com.ant.webPage.dao;

import com.ant.entity.Totices;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 公告dao
 * @author Billing
 * @date 2018/8/13 19:22
 */
public interface ToticesDao extends BaseMapper<Totices> {

    public List<Totices> findList();

    Totices selectOneById(Integer toticesId);
}
