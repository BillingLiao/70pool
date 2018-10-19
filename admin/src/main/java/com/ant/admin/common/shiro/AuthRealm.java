package com.ant.admin.common.shiro;

import com.ant.admin.common.utils.Constant;
import com.ant.admin.dao.MenuDao;
import com.ant.admin.dao.SysUserDao;
import com.ant.entity.Menu;
import com.ant.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author zhaopinchao
 * @date 2018-7-28 08:04
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private SysUserDao sysuserDao;

    //{super.setName("authRealm");}

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
/*
        //获取session中的用户
        String user_name=(String) principals.fromRealm(this.getClass().getName()).iterator().next();
        //通过用户名来查询用户所具有的所有操作权限
        Set<String> permissions = new HashSet<>();
        permissions = funcDao.getFuncByUserName(user_name);

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //将权限放入shiro中.
        info.addStringPermissions(permissions);
        //将菜单放入shiro的session中
        //SecurityUtils.getSubject().getSession().setAttribute("user",permissions);
        return info;
*/
        //User user = (User)principals.getPrimaryPrincipal();
        SysUser sysUser = ShiroUtils.getUser();//(SysUser) SecurityUtils.getSubject().getSession().getAttribute("sysUser");

        Integer userId = sysUser.getUserId();

        List<String> permsList;
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            List<Menu> menuList = menuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (Menu menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysuserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;

    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        //查询系统用户信息
        SysUser sysUser = new SysUser();
        sysUser.setUserName(token.getUsername());
        sysUser = sysuserDao.selectOne(sysUser);
        //账号不存在
        if (sysUser == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //账号锁定
        if (sysUser.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());

        //第一个参数使用user对象，原来使用的是user.getUserName()
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),this.getClass().getName());

        //将用户登录信息放入shiro的session中
        SecurityUtils.getSubject().getSession().setAttribute("sysUser", sysUser);

        return info;
    }
}

