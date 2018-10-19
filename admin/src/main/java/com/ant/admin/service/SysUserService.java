package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 系统用户service
 * @author Billing
 * @date 2018/9/6 11:23
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 查询用户的所有菜单ID
     */
    List<Integer> queryAllMenuId(Integer userId);


    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存用户
     */
    void save(SysUser sysUser);

    /**
     * 修改用户
     */
    void update(SysUser sysUser);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Integer userId, String password, String newPassword);
}

