package com.ant.webPage.controller;

import com.ant.entity.OrderRecord;
import com.ant.entity.User;
import com.ant.webPage.service.OrderRecordService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单controller
 * @author Billing
 * @date 2018/9/11 20:24
 */
@RestController
@RequestMapping("/orderRecord")
@CrossOrigin
public class OrderRecordController {

    @Autowired
    private OrderRecordService orderRecordService;

    /**
     * 查询订单记录
     * @param user
     * @return
     */
    @RequestMapping("/my")
    public Result record(@SessionAttribute User user){
        Integer userId = user.getUserId();
        List<OrderRecord> orderRecordList = orderRecordService.selectOrderRecord(userId);
        return Result.ok().put("orderRecordList",orderRecordList);
    }
}
