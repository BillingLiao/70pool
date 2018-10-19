package com.ant.admin.service.impl;

import com.ant.admin.common.shiro.ShiroUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.ToticesDao;
import com.ant.entity.SysUser;
import com.ant.entity.Totices;
import com.ant.entity.User;
import com.ant.admin.service.ToticesService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Service("toticesService")
public class ToticesServiceImpl extends ServiceImpl<ToticesDao, Totices> implements ToticesService{

    @Resource
    private ToticesDao toticesDao;

    @Override
    public Page<Totices> queryPage(Map<String, Object> params, Wrapper<Totices> wrapper) {
        Page<Totices> page =new Query<Totices>(params).getPage();
        return page.setRecords(baseMapper.selectToticesList(page,wrapper));
    }

    /**
     * 保存
     * @param totices
     */
    @Override
    public void insertTotices(Totices totices) {
        //设置创建日期
        totices.setPublishDate(new Date());
        //获取登录用户信息
        SysUser user = ShiroUtils.getUser();
        //SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("sysUser");
        totices.setPublishUser(user.getUserId());
        toticesDao.insert(totices);
    }

    @Override
    public void updateTotices(Totices totices) {
        SysUser user = ShiroUtils.getUser();
        //SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("sysUser");
        totices.setUpdateUser(user.getUserId());
        totices.setUpdateDate(new Date());
        toticesDao.updateAllColumnById(totices);
    }

    @Override
    public void deleteTotices(Integer[] toticesIds) {
        toticesDao.deleteBatchIds(Arrays.asList(toticesIds));
    }

    @Override
    public Totices infoTotices(Integer toticesId) {
        Totices totices = toticesDao.selectById(toticesId);
        return totices;
    }
}
