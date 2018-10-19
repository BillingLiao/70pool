package com.ant.webPage.dao;

import com.ant.entity.FinancialProduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;


/**
 * 理财产品明细
 *
 * @author Billing
 * @date 2018/8/20 19:19
 */
public interface FinancialProductDao extends BaseMapper<FinancialProduct> {
    /**
     * 根据产品id查询理财产品
     * @param productId
     * @return
     */
    FinancialProduct selectByProductId(Integer productId);

    List<FinancialProduct> findList();

    List<FinancialProduct> findFour();
}
