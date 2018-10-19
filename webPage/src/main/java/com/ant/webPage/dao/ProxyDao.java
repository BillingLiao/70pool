package com.ant.webPage.dao;

import com.ant.entity.Proxy;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 代理表
 * @author Billing
 * @date 2018/8/13 19:24
 */
public interface ProxyDao extends BaseMapper<Proxy> {
    Proxy selectByUserId(Integer userId);
}
