package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.TstimateDao;
import com.ant.admin.service.TstimateService;
import com.ant.entity.Tstimate;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:40
 */
@Service("tstimateService")
public class TstimateServiceImpl extends ServiceImpl<TstimateDao,Tstimate> implements TstimateService {


    @Autowired
    private TstimateDao tstimateDao ;

    @Override
    public Tstimate infoTstimate(Integer tstimateId) {
        Tstimate tstimate = tstimateDao.selectById(tstimateId);
        return tstimate;
    }

    @Override
    public void insertTstimate(Tstimate tstimate){
        tstimate.setCreateTime(new Date());
        tstimateDao.insert(tstimate);
    }

    @Override
    public void updateTstimate(Tstimate tstimate) {
        tstimateDao.updateAllColumnById(tstimate);
    }

    @Override
    public void deleteTstimate(Integer[] tstimateIds) {
        tstimateDao.deleteBatchIds(Arrays.asList(tstimateIds));
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<Tstimate> page = this.selectPage(
                new Query<Tstimate>(params).getPage(),
                new EntityWrapper<Tstimate>()
        );
        return new PageUtils(page);
    }
}
