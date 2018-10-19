package com.ant.webPage.service.impl;

import com.ant.entity.PresentRecord;
import com.ant.webPage.dao.PresentRecordDao;
import com.ant.webPage.service.PresentRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("presentRecordService")
public class PresentRecordServiceImpl extends ServiceImpl<PresentRecordDao, PresentRecord> implements PresentRecordService {

    @Autowired
    private PresentRecordDao presentRecordDao;

    @Override
    public List<PresentRecord> selectPresentRecord(Integer userId) {
        List<PresentRecord> presentRecordList = presentRecordDao.selectPresentRecord(userId);
        return presentRecordList;
    }
}
