package com.ant.admin.controller;

import com.ant.admin.common.annotation.SysLog;
import com.ant.admin.common.shiro.ShiroUtils;
import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.common.validator.Assert;
import com.ant.common.validator.ValidatorUtils;
import com.ant.common.validator.group.AddGroup;
import com.ant.common.validator.group.UpdateGroup;
import com.ant.admin.service.SysUserService;
import com.ant.admin.service.UserRoleService;
import com.ant.entity.SysUser;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/15 11:59
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends  AbstractController{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = sysUserService.queryPage(params);

        return Result.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public Result info(){
        return Result.ok().put("sysUser", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public Result password(String password, String newPassword){
        Assert.isBlank(newPassword, "新密码不为能空");

        //原密码
        password = ShiroUtils.sha256(password, getUser().getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if(!flag){
            return Result.error("原密码不正确");
        }

        return Result.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    @ResponseBody
    public Result info(@PathVariable("userId") Integer userId){
        SysUser sysUser = sysUserService.selectById(userId);

        //获取用户所属的角色列表
        List<Integer> roleIdList = userRoleService.queryRoleIdList(userId);
        sysUser.setRoleIdList(roleIdList);

        return Result.ok().put("sysUser", sysUser);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    @ResponseBody
    public Result save(@RequestBody SysUser sysUser){
        ValidatorUtils.validateEntity(sysUser, AddGroup.class);

        sysUserService.save(sysUser);

        return Result.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    @ResponseBody
    public Result update(@RequestBody SysUser sysUser){
        ValidatorUtils.validateEntity(sysUser, UpdateGroup.class);

        sysUserService.update(sysUser);

        return Result.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] userIds){
        if(ArrayUtils.contains(userIds, 1)){
            return Result.error("系统管理员不能删除");
        }

        if(ArrayUtils.contains(userIds, getUserId())){
            return Result.error("当前用户不能删除");
        }

        sysUserService.deleteBatchIds(Arrays.asList(userIds));

        return Result.ok();
    }
}
