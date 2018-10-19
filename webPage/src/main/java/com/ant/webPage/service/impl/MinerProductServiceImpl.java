package com.ant.webPage.service.impl;

import com.ant.entity.MinerProduct;
import com.ant.webPage.dao.MinerProductDao;
import com.ant.webPage.service.MinerProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("minerProductService")
public class MinerProductServiceImpl extends ServiceImpl<MinerProductDao, MinerProduct> implements MinerProductService {

    @Autowired
    private MinerProductDao minerProductDao;

    @Override
    public List<MinerProduct> findList() {
        return minerProductDao.findList();
    }

    @Override
    public MinerProduct findOne(Integer productId) {
        return minerProductDao.selectByProductId(productId);
    }
}
