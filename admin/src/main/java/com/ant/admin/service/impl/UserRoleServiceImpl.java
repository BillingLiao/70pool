package com.ant.admin.service.impl;

import com.ant.admin.common.utils.MapUtils;
import com.ant.admin.dao.UserRoleDao;
import com.ant.entity.UserRole;
import com.ant.admin.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  用户与角色关系
 *
 * @author Billing
 * @date 2018/8/17 16:43
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

    @Override
    public void saveOrUpdate(Integer userId, List<Integer> roleIdList) {
        //先删除用户与角色关系
        this.deleteByMap(new MapUtils().put("user_id", userId));

        if(roleIdList == null || roleIdList.size() == 0){
            return ;
        }

        //保存用户与角色关系
        List<UserRole> list = new ArrayList<>(roleIdList.size());
        for(Integer roleId : roleIdList){
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            list.add(userRole);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Integer> queryRoleIdList(Integer userId) {
        return baseMapper.queryRoleIdList(userId);
    }

    @Override
    public int deleteBatch(Integer[] roleIds){
        return baseMapper.deleteBatch(roleIds);
    }
}

