package com.ant.webPage.service.impl;

import com.ant.entity.BtcAddr;
import com.ant.entity.User;
import com.ant.webPage.dao.BtcAddrDao;
import com.ant.webPage.service.BtcAddrService;
import com.ant.webPage.tool.CheckTool;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("btcAddrService")
public class BtcAddrServiceImpl extends ServiceImpl<BtcAddrDao, BtcAddr> implements BtcAddrService {

    @Autowired
    private BtcAddrDao btcAddrDao;

    @Override
    public Result addBtcAddr(User user, String addr) {
        if(!CheckTool.isString(addr)){
            return Result.error("钱包地址不能为空");
        }
        BtcAddr btcAddrEntity = new BtcAddr();
        btcAddrEntity.setUserId(user.getUserId());
        btcAddrEntity.setAddr(addr);
        btcAddrEntity.setCreateAt(new Date());
        btcAddrDao.insert(btcAddrEntity);
        return Result.ok("添加成功");
    }

    @Override
    public Result updateBtcAddr(User user, String addr, Integer btcAddrId) {
        if(!CheckTool.isString(addr)){
            return Result.error("钱包地址不能为空");
        }
        BtcAddr btcAddrEntity = btcAddrDao.selectById(btcAddrId);
        if(btcAddrEntity == null){
            return Result.error("修改的信息不存在");
        }
        BtcAddr btcAddrUpade = new BtcAddr();
        btcAddrUpade.setUserId(user.getUserId());
        btcAddrUpade.setAddr(addr);
        btcAddrUpade.setBtcAddrId(btcAddrId);
        btcAddrDao.updateAllColumnById(btcAddrUpade);
        return Result.ok("修改成功");
    }

    @Override
    public BtcAddr selectBtcAddrByUserId(Integer userId) {
        return btcAddrDao.selectBtcAddrByUserId(userId);
    }
}
