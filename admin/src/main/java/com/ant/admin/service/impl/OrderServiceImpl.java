package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.OrderDao;
import com.ant.admin.dao.OrderRecordDao;
import com.ant.admin.dao.ProductDao;
import com.ant.admin.dao.UserDao;
import com.ant.entity.Order;
import com.ant.entity.OrderRecord;
import com.ant.entity.Product;
import com.ant.entity.User;
import com.ant.admin.service.OrderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderRecordDao orderRecordDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String categoryName=(String)params.get("categoryName");
        String orderStatus=(String)params.get("orderStatus");
        EntityWrapper<Order> orderEntityWrapper =new EntityWrapper<Order>();

        orderEntityWrapper.like("c.category_name", categoryName);
        orderEntityWrapper.eq(StringUtils.isNotBlank(orderStatus), "o.order_status", orderStatus);
        Page<Order> page =new Query<Order>(params).getPage();
        List<Order> list = baseMapper.selectOrderList(page,orderEntityWrapper);
        page.setRecords(list);
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void updateTypeByTime() {
        Date createTime = new Date();
        /**
         *  判断理财产品是否到期
         *  将今日到期产品的本金与收益插入用户余额
         */
        List<Order> orderList =  orderDao.selectByTime();
        Iterator<Order> iter = orderList.iterator();
        while(iter.hasNext()){  //执行过程中会执行数据锁定，性能稍差，若在循环过程中要去掉某个元素只能调用iter.remove()方法。
            Order order = iter.next();
            User user = userDao.selectById(order.getUserId());
            BigDecimal actualReceipts =  order.getActualReceipts(); //订单实付款(本金)
            BigDecimal maturityIncome = order.getMaturityIncome(); //到期收益
            BigDecimal cny = user.getCny().add(actualReceipts.add(maturityIncome));
            user.setCny(cny);
            userDao.updateAllColumnById(user);
        }
        /**
         * 到期后更改订单状态
         */
        orderDao.updateTypeByTime();
        orderRecordDao.insertRecord();
    }
}
