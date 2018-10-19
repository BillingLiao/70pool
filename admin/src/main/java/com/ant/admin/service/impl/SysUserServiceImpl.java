package com.ant.admin.service.impl;

import com.ant.admin.common.shiro.ShiroUtils;
import com.ant.admin.common.utils.Constant;
import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.SysUserDao;
import com.ant.admin.service.SysUserService;
import com.ant.admin.service.UserRoleService;
import com.ant.entity.SysUser;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:43
 */
@Service("sysUerService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao,SysUser> implements SysUserService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<Integer> queryAllMenuId(Integer userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String userName = (String)params.get("userName");

        Page<SysUser> page = this.selectPage(
                new Query<SysUser>(params).getPage(),
                new EntityWrapper<SysUser>()
                        .like(StringUtils.isNotBlank(userName),"userName", userName)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );
        /*for(User sysUserEntity : page.getRecords()){
            User sysDeptEntity = sysDeptService.selectById(sysUserEntity.getDeptId());
            sysUserEntity.setDeptName(sysDeptEntity.getName());
        }*/
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUser user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        this.insert(user);

        //保存用户与角色关系
        userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUser user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        }
        this.updateById(user);

        //保存用户与角色关系
        userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public boolean updatePassword(Integer userId, String password, String newPassword) {
        SysUser user = new SysUser();
        user.setPassword(newPassword);
        return this.update(user,
                new EntityWrapper<SysUser>().eq("user_id", userId).eq("password", password));
    }
}
