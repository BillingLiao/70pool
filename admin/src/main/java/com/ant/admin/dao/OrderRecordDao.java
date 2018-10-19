package com.ant.admin.dao;

import com.ant.entity.OrderRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 订单记录dao
 * @author Billing
 * @date 2018/9/15 14:14
 */
public interface OrderRecordDao extends BaseMapper<OrderRecord> {
     void insertRecord();
}
