package com.ant.admin.service;

import com.ant.entity.RoleMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author Billing
 * @date 2018/8/16 16:35
 */
public interface RoleMenuService extends IService<RoleMenu> {

    void saveOrUpdate(Integer roleId, List<Integer> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Integer> queryMenuIdList(Integer roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Integer[] roleIds);

}
