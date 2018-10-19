package com.ant.webPage.service.impl;

import com.ant.entity.FinancialProduct;
import com.ant.webPage.dao.FinancialProductDao;
import com.ant.webPage.service.FinancialProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("financialProductService")
public class FinancialProductServiceImpl extends ServiceImpl<FinancialProductDao, FinancialProduct> implements FinancialProductService {

    @Autowired
    private FinancialProductDao financialProductDao;

    @Override
    public List<FinancialProduct> findList() {
        return financialProductDao.findList();
    }

    @Override
    public FinancialProduct findOne(Integer productId) {
        return financialProductDao.selectByProductId(productId);
    }

    @Override
    public List<FinancialProduct> findFour() {
        return financialProductDao.findFour();
    }
}
