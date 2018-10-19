package com.ant.webPage.dao;

import com.ant.entity.MinerProduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 矿机
 * @author Billing
 * @date 2018/8/20 19:19
 */
public interface MinerProductDao extends BaseMapper<MinerProduct> {

    /**
     * 根据产品id查询矿机产品
     * @param productId
     * @return
     */
    MinerProduct selectByProductId(Integer productId);

    List<MinerProduct> findList();
}
