package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.common.validator.ValidatorUtils;
import com.ant.admin.dao.PresentRecordDao;
import com.ant.admin.service.PutForwardService;
import com.ant.entity.PresentRecord;
import com.ant.entity.PutForward;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * 提现记录controller
 * @author Billing
 * @date 2018/8/15 11:54
 */
@RestController
@RequestMapping("/putForward")
public class PutForwardController extends  AbstractController{

    @Autowired
    private PutForwardService putForwardService;

    @Autowired
    private PresentRecordDao presentRecordDao;

    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("putForward:list")
    public Result list(@RequestParam Map<String,Object> params){
        PageUtils page=putForwardService.queryPage(params);
        return Result.ok().put("page", page);
    }

    /**
     * 更新提现状态
     */
    @RequestMapping("/status")
    @RequiresPermissions("putForward:status:update")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@RequestBody PutForward putForward) throws ParseException {
        //校验类型
        ValidatorUtils.validateEntity(putForward);
        Date createTime = new Date();
        if(putForward.getForwardStatus() == 3){
            putForward.setCompletionTime(createTime);
        }
        putForwardService.updateAllColumnById(putForward);
        PresentRecord presentRecord = new PresentRecord(putForward.getPutForwardId(),putForward.getForwardStatus(),createTime);
        presentRecordDao.insert(presentRecord);
        return Result.ok("更新成功");
    }

    /**
     * 根据id查询单条记录
     * @param putForwardId
     * @return
     */
    @RequestMapping("/info/{putForwardId}")
    @RequiresPermissions("putForward:info")
    public Result info(@PathVariable("putForwardId") Integer putForwardId){
        PutForward putForward =  putForwardService.selectById(putForwardId);
        return Result.ok().put("putForward",putForward);
    }
}
