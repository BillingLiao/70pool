package com.ant.webPage.controller;

import com.ant.entity.PresentRecord;
import com.ant.entity.User;
import com.ant.webPage.service.PresentRecordService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提现记录controller
 * @author Billing
 * @date 2018/9/11 20:24
 */
@RestController
@RequestMapping("/presentRecord")
@CrossOrigin
public class PresentRecordController {

    @Autowired
    private PresentRecordService presentRecordService;

    /**
     * 查询订单记录
     * @param user
     * @return
     */
    @RequestMapping("/my")
    public Result record(@SessionAttribute User user){
        Integer userId = user.getUserId();
        List<PresentRecord> presentRecordList = presentRecordService.selectPresentRecord(userId);
        return Result.ok().put("presentRecordList",presentRecordList);
    }
}
