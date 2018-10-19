package com.ant.webPage.service;

import com.ant.entity.Proxy;
import com.baomidou.mybatisplus.service.IService;

/**
 * 代理表
 * @author Billing
 * @date 2018/8/13 19:33
 */
public interface ProxyService extends IService<Proxy> {

    /**
     * 通过用户id查询代理表
     * @param userId
     * @return
     */
    Proxy selectByUserId(Integer userId);
}
