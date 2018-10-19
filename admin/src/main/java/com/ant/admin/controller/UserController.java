package com.ant.admin.controller;

import com.ant.admin.common.utils.MPUtil;
import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.service.UserService;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.User;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 用户表
 * 后端接口
 * @author xuchen
 * @email 171998110@qq.com
 * @date 2018-06-30 13:40:24
 */
@RestController
@RequestMapping("user/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/page")
    @RequiresPermissions("user:user:list")
    public Result page(@RequestParam Map<String, Object> params, User user){
 
       
    	PageUtils page = userService.queryPage(params, user);
    
        return Result.ok().put("page", page);
        
    }

	/**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:user:list")
    public Result list(User user){
       	EntityWrapper<User> ew = new EntityWrapper<User>();
      	ew.allEq(MPUtil.allEQMapPre( user, "user")); 
        return Result.ok().put("data",  userService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    @RequiresPermissions("user:user:info")
    public Result query(User user){
        EntityWrapper<User> ew = new EntityWrapper<User>();
 		ew.allEq(MPUtil.allEQMapPre( user, "user"));
		User  userView =  userService.selectView(ew);
		return Result.ok("查询用户表成功").put("data",  userView);
    }
	
    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("user:user:info")
    public Result info(@PathVariable("userId") Integer userId){
        User user = userService.selectById(userId);

        return Result.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:user:save")
    public Result save(@RequestBody User user){
    	ValidatorUtils.validateEntity(user);
        userService.insert(user);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:user:update")
    public Result update(@RequestBody User user){
        ValidatorUtils.validateEntity(user);
        userService.updateAllColumnById(user);//全部更新
        
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:user:delete")
    public Result delete(@RequestBody Integer[] userIds){
        userService.deleteBatchIds(Arrays.asList(userIds));

        return Result.ok();
    }

}
