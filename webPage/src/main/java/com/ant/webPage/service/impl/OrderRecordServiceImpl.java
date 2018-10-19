package com.ant.webPage.service.impl;

import com.ant.entity.*;
import com.ant.webPage.dao.*;
import com.ant.webPage.service.OrderRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("orderRecordService")
public class OrderRecordServiceImpl extends ServiceImpl<OrderRecordDao, OrderRecord> implements OrderRecordService {

    @Autowired
    private OrderRecordDao orderRecordDao;

    @Override
    public List<OrderRecord> selectOrderRecord(Integer userId) {
        List<OrderRecord> orderRecordList = orderRecordDao.selectOrderRecord(userId);
        return orderRecordList;
    }
}
