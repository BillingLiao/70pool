package com.ant.webPage.service.impl;

import com.ant.entity.Tstimate;
import com.ant.webPage.dao.TstimateDao;
import com.ant.webPage.service.TstimateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Billing
 * @date 2018/9/14 9:52
 */
@Service("tstimateService")
public class TstimateServiceImpl extends ServiceImpl<TstimateDao, Tstimate> implements TstimateService {

    @Autowired
    private TstimateDao tstimateDao;

    @Override
    public Tstimate findOne() {
        return tstimateDao.findFirst();
    }
}
