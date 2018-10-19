package com.ant.webPage.service;

import com.ant.entity.MinerProduct;
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
public interface MinerProductService extends IService<MinerProduct> {

    public List<MinerProduct> findList();

    public MinerProduct findOne(Integer productId);
}
