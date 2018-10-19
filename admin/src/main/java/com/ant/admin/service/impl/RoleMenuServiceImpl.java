package com.ant.admin.service.impl;

import com.ant.admin.dao.RoleMenuDao;
import com.ant.entity.RoleMenu;
import com.ant.admin.service.RoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author Billing
 * @date 2018/8/16 17:56
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Integer roleId, List<Integer> menuIdList) {
        //先删除角色与菜单关系
        deleteBatch(new Integer[]{roleId});

        if(menuIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        List<RoleMenu> list = new ArrayList<>(menuIdList.size());
        for(Integer menuId : menuIdList){
            RoleMenu sysRoleMenuEntity = new RoleMenu();
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuEntity.setRoleId(roleId);

            list.add(sysRoleMenuEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Integer> queryMenuIdList(Integer roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Integer[] roleIds){
        return baseMapper.deleteBatch(roleIds);
    }

}