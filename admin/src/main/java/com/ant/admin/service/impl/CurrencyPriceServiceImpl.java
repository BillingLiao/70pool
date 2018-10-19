package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.CurrencyPriceDao;
import com.ant.admin.service.CurrencyPriceService;
import com.ant.entity.CurrencyPrice;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Service("currencyPriceService")
public class CurrencyPriceServiceImpl extends ServiceImpl<CurrencyPriceDao, CurrencyPrice> implements CurrencyPriceService {

    @Resource
    private CurrencyPriceDao currencyPriceDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CurrencyPrice> page = this.selectPage(
                new Query<CurrencyPrice>(params).getPage(),
                new EntityWrapper<CurrencyPrice>()
        );
        return new PageUtils(page);
    }

    /**
     * 保存
     * @param currencyPrice
     */
    @Override
    public void insertCurrencyPrice(CurrencyPrice currencyPrice) {
        //设置创建日期
        currencyPrice.setCreateTime(new Date());
        currencyPriceDao.insert(currencyPrice);
    }

    @Override
    public void updateCurrencyPrice(CurrencyPrice currencyPrice) {
        currencyPriceDao.updateAllColumnById(currencyPrice);
    }

    @Override
    public void deleteCurrencyPrice(Integer[] currencyPriceIds) {
        currencyPriceDao.deleteBatchIds(Arrays.asList(currencyPriceIds));
    }

    @Override
    public CurrencyPrice infoCurrencyPrice(Integer currencyPriceId) {
        CurrencyPrice currencyPrice = currencyPriceDao.selectById(currencyPriceId);
        return currencyPrice;
    }
}
