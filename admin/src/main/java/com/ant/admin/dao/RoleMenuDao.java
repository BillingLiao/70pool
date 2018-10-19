package com.ant.admin.dao;

import com.ant.entity.RoleMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/16 17:59
 */
public interface RoleMenuDao extends BaseMapper<RoleMenu> {

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Integer> queryMenuIdList(Integer roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Integer[] roleIds);
}
