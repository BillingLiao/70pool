package com.ant.webPage.service;

import com.ant.entity.CloudProduct;
import com.ant.entity.Product;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface CloudProductService extends IService<CloudProduct> {

    public List<CloudProduct> findList();

    public CloudProduct findOne(Integer productId);

    public CloudProduct findFirst();
}
