package com.ant.admin.dao;

import com.ant.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseMapper<User> {
    List<User> selectListVO(@Param("ew") Wrapper<User> wrapper);

    User selectVO(@Param("ew") Wrapper<User> wrapper);

    List<User> selectListView(@Param("ew") Wrapper<User> wrapper);

    List<User> selectListView(Pagination page, @Param("ew") Wrapper<User> wrapper);

    User selectView(@Param("ew") Wrapper<User> wrapper);
}
