package com.ant.webPage.service.impl;

import com.ant.entity.Totices;
import com.ant.webPage.dao.ToticesDao;
import com.ant.webPage.service.ToticesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("toticesService")
public class ToticesServiceImpl extends ServiceImpl<ToticesDao, Totices> implements ToticesService {

    @Autowired
    private ToticesDao toticesDao;

    @Override
    public List<Totices> findList() {
        return toticesDao.findList();
    }

    @Override
    public Totices selectOneById(Integer toticesId) {
        return toticesDao.selectOneById(toticesId);
    }
}
