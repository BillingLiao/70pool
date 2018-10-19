package com.ant.admin.dao;

import com.ant.entity.CurrencyPrice;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 比特币价格Dao
 *
 * @author Billing
 * @date 2018-8-13 16:25
 */
@Component
public interface CurrencyPriceDao extends BaseMapper<CurrencyPrice> {

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    List<CurrencyPrice> selectCurrencyPriceList(Page<CurrencyPrice> page, @Param("ew") Wrapper<CurrencyPrice> wrapper);

}
