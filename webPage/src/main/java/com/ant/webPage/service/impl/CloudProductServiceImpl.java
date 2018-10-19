package com.ant.webPage.service.impl;

import com.ant.entity.CloudProduct;
import com.ant.entity.Totices;
import com.ant.webPage.dao.CloudProductDao;
import com.ant.webPage.dao.ToticesDao;
import com.ant.webPage.service.CloudProductService;
import com.ant.webPage.service.ToticesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("cloudProductService")
public class CloudProductServiceImpl extends ServiceImpl<CloudProductDao, CloudProduct> implements CloudProductService {

    @Autowired
    private CloudProductDao cloudProductDao;

    @Override
    public List<CloudProduct> findList() {
        List<CloudProduct> list= cloudProductDao.findList();
        return cloudProductDao.findList();
    }

    @Override
    public CloudProduct findOne(Integer productId) {
        return cloudProductDao.selectByProductId(productId);
    }

    @Override
    public CloudProduct findFirst() {
        return cloudProductDao.findFirst();
    }
}
