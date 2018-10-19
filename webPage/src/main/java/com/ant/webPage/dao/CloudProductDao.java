package com.ant.webPage.dao;

import com.ant.entity.CloudProduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 云算力
 *
 * @author Billing
 * @date 2018/8/20 19:19
 */
public interface CloudProductDao extends BaseMapper<CloudProduct> {

    CloudProduct selectByProductId(Integer productId);

    CloudProduct findFirst();

    List<CloudProduct> findList();
}
