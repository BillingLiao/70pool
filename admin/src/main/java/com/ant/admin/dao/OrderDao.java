package com.ant.admin.dao;

import com.ant.entity.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:22
 */
public interface OrderDao extends BaseMapper<Order> {

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    public List<Order> selectOrderList(Pagination page, @Param("ew") Wrapper<Order> wrapper);

    public void updateTypeByTime();

    public List<Order> selectByTime();
}
