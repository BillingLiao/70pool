package com.ant.admin.controller;

import com.ant.admin.common.annotation.SysLog;
import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.Role;
import com.ant.admin.service.RoleMenuService;
import com.ant.admin.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/15 11:59
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController extends AbstractController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = roleService.queryPage(params);
        return Result.ok().put("page", page);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public Result select(){
        List<Role> list = roleService.selectList(null);

        return Result.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public Result info(@PathVariable("roleId") Integer roleId){
        Role role = roleService.selectById(roleId);

        //查询角色对应的菜单
        List<Integer> menuIdList = roleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);


        return Result.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    public Result save(@RequestBody Role role){
        ValidatorUtils.validateEntity(role);

        roleService.save(role);

        return Result.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:update")
    public Result update(@RequestBody Role role){
        ValidatorUtils.validateEntity(role);

        roleService.update(role);

        return Result.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@RequestBody Integer[] roleIds){
        roleService.deleteBatch(roleIds);

        return Result.ok();
    }
}

