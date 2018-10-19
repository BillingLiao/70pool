package com.ant.admin.dao;

import com.ant.entity.CloudProduct;
import com.ant.entity.Product;
import com.ant.entity.Tstimate;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 预估收益
 *
 * @author Billing
 * @date 2018/8/20 19:19
 */
public interface TstimateDao extends BaseMapper<Tstimate> {

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    List<Tstimate> selectTstimateList(Page<Tstimate> page,@Param("ew") Wrapper<Tstimate> wrapper);
}
