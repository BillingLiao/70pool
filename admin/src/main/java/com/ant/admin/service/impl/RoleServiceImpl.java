package com.ant.admin.service.impl;

import com.ant.admin.common.annotation.DataFilter;
import com.ant.admin.common.utils.Constant;
import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.RoleDao;
import com.ant.entity.Role;
import com.ant.admin.service.RoleMenuService;
import com.ant.admin.service.RoleService;
import com.ant.admin.service.UserRoleService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 角色
 *
 * @author Billing
 * @date 2018/8/16 17:56
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    @DataFilter(user = false)
    public PageUtils queryPage(Map<String, Object> params) {
        String roleName = (String)params.get("roleName");

        Page<Role> page = this.selectPage(
                new Query<Role>(params).getPage(),
                new EntityWrapper<Role>()
                        .like(StringUtils.isNotBlank(roleName),"role_name", roleName)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Role role) {
        role.setCreateTime(new Date());
        this.insert(role);

        //保存角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role role) {
        this.updateAllColumnById(role);

        //更新角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] roleIds) {
        //删除角色
        this.deleteBatchIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        roleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        userRoleService.deleteBatch(roleIds);
    }


}
