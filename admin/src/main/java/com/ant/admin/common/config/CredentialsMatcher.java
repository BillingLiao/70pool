package com.ant.admin.common.config;

import com.ant.admin.common.shiro.ShiroUtils;
import com.ant.entity.SysUser;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaopinchao
 * @date 2018-7-28 08:15
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    private static final Logger logger = LoggerFactory.getLogger(CredentialsMatcher.class);

    //校验
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        SysUser user = (SysUser) info.getPrincipals().getPrimaryPrincipal();
        //用户输入密码
        String inPassword = new String(utoken.getPassword());
        //盐值
        String salt = user.getSalt();
        //盐值加密
        String password = ShiroUtils.sha256(inPassword,salt);
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();  // user.getPassword();
        //进行密码的比对
        logger.info("盐值：{}",salt);
        logger.info("输入密码：{}",password);
        logger.info("数据库密码：{}",dbPassword);
        return this.equals(password, dbPassword);
    }
}
