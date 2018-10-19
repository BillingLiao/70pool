package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.CurrencyPrice;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

public interface CurrencyPriceService extends IService<CurrencyPrice> {

    PageUtils queryPage(Map<String, Object> params);

    void insertCurrencyPrice(CurrencyPrice currencyPrice);

    void updateCurrencyPrice(CurrencyPrice currencyPrice);

    void deleteCurrencyPrice(Integer[] currencyPriceIds);

    CurrencyPrice infoCurrencyPrice(Integer currencyPriceId);
}

