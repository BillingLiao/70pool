package com.ant.admin.service;

import com.ant.entity.UserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/17 16:05
 */
public interface UserRoleService extends IService<UserRole> {

    void saveOrUpdate(Integer userId, List<Integer> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Integer> queryRoleIdList(Integer userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Integer[] roleIds);

}
