package com.ant.admin.dao;

import com.ant.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author Billing
 * @date 2018/8/17 15:11
 */
public interface UserRoleDao extends BaseMapper<UserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Integer> queryRoleIdList(Integer userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Integer[] roleIds);
}
