package com.ant.webPage.dao;

import com.ant.entity.OrderRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 订单dao
 * @author Billing
 * @date 2018/8/13 19:22
 */
public interface OrderRecordDao extends BaseMapper<OrderRecord> {

    List<OrderRecord> selectOrderRecord(Integer userId);
}
