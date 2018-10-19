package com.ant.webPage.service;

import com.ant.entity.User;
import com.ant.webPage.model.Account;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author Billing
 * @date 2018/9/5 14:55
 */
public interface UserService extends IService<User> {

    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    public User findOne(int userId);

    /**
     * 登录
     * @param telphone
     * @param password
     * @return
     */
    public Result login(String telphone, String password);

    /**
     * 通过手机号码查找用户
     * @param phone
     * @return
     */
    public User findByPhone(String phone);

    /**
     * 通过邀请码查找用户
     * @param invitationCode
     * @return
     */
    public User selectByInvitationCode(String invitationCode);

    /**
     * 通过用户id 查找用户的余额算力
     * @param userId
     * @return
     */
    Account selectAccountByUserId(Integer userId);
}
