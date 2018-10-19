package com.ant.admin.dao;

import com.ant.entity.FinancialProduct;
import com.ant.entity.Product;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 理财产品明细
 *
 * @author Billing
 * @date 2018/8/20 19:19
 */
public interface FinancialProductDao extends BaseMapper<FinancialProduct> {

    /**
     * 分页查询理财产品列表
     * @param page
     * @param wrapper
     * @return
     */
    List<FinancialProduct> selectProductList(Page<FinancialProduct> page,@Param("ew") Wrapper<Product> wrapper);

    /**
     * 根据产品id查询理财产品
     * @param productId
     * @return
     */
    FinancialProduct selectByProductId(Integer productId);
}
