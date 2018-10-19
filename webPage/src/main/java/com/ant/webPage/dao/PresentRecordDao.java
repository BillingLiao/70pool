package com.ant.webPage.dao;

import com.ant.entity.PresentRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * @author Billing
 * @date 2018/9/15 14:52
 */
public interface PresentRecordDao extends BaseMapper<PresentRecord> {

    List<PresentRecord> selectPresentRecord(Integer userId);
}
