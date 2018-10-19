package com.ant.admin.dao;

import com.ant.entity.CloudProduct;
import com.ant.entity.Product;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 云算力
 *
 * @author Billing
 * @date 2018/8/20 19:19
 */
public interface CloudProductDao extends BaseMapper<CloudProduct> {

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    List<CloudProduct> selectProductList(Page<CloudProduct> page,@Param("ew") Wrapper<Product> wrapper);

    /**
     * 根据产品id查询云算力产品
     * @param productId
     * @return
     */
    CloudProduct selectByProductId(Integer productId);
}
