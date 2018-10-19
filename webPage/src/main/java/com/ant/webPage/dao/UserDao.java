package com.ant.webPage.dao;

import com.ant.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author Billing
 * @date 2018/9/5 15:21
 */
public interface UserDao extends BaseMapper<User> {

    public User findOneById(int userId);

    public User findOneByPhone(String telphone);

    public User selectByInvitationCode(String invitationCode);
}
