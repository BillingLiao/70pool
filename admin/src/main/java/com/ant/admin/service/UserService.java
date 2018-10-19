package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.User;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户
 * @author Billing
 * @date 2018/9/11 18:05
 */
public interface UserService extends IService<User> {

    PageUtils queryPage(Map<String, Object> params);

    List<User> selectListVO(Wrapper<User> wrapper);

    User selectVO(@Param("ew") Wrapper<User> wrapper);

    List<User> selectListView(Wrapper<User> wrapper);

    User selectView(@Param("ew") Wrapper<User> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<User> wrapper);

    PageUtils queryPage(Map<String, Object> params, User userModel);
}
