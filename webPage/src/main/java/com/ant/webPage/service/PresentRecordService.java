package com.ant.webPage.service;

import com.ant.entity.PresentRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Billing
 * @date 2018/9/17 11:39
 */
public interface PresentRecordService  extends IService<PresentRecord> {

    List<PresentRecord> selectPresentRecord(Integer userId);
}
