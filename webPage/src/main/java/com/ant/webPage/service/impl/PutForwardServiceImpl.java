package com.ant.webPage.service.impl;

import com.ant.entity.PresentRecord;
import com.ant.entity.PutForward;
import com.ant.entity.User;
import com.ant.webPage.dao.PresentRecordDao;
import com.ant.webPage.dao.PutForwardDao;
import com.ant.webPage.dao.UserDao;
import com.ant.webPage.service.PutForwardService;
import com.ant.webPage.util.Result;
import com.ant.webPage.util.SerialNumberUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("putForwardService")
public class PutForwardServiceImpl extends ServiceImpl<PutForwardDao, PutForward> implements PutForwardService {

    @Autowired
    private PutForwardDao putForwardDao;

    @Autowired
    private PresentRecordDao presentRecordDao;

    @Autowired
    private UserDao userDao;

    /**
     * 提现btc
     * @param user
     * @param btcTrue
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addBtcPutForward(User user, Integer forwardType, BigDecimal btcTrue) {
        BigDecimal btc = btcTrue.multiply(new BigDecimal(1.005));
        Integer userId = user.getUserId();
        User selectUser = userDao.selectById(userId);
        BigDecimal btcBalance = selectUser.getBtc();
        //BigDecimal cnyBalance = selectUser.getCny();
        int num = btc.compareTo(btcBalance);
        if(num == 1){
            return Result.error("请填入正确的金额");
        }

        /**
         * 更新用户余额
         */
        btcBalance = btcBalance.subtract(btc);
        selectUser.setBtc(btcBalance);
        userDao.updateAllColumnById(selectUser);

        String putForwardCode = SerialNumberUtil.toSerialNumber(user.getUserId());
        //生成订单号
        String putForwardNo;
        putForwardNo = "BTC" + putForwardCode;
        Date createTime = new Date();
        PutForward putForward = new PutForward(putForwardNo,user.getUserId(),forwardType,0,btc,btcTrue,createTime);
        putForwardDao.insert(putForward);
        PresentRecord presentRecord = new PresentRecord(putForward.getPutForwardId(),0,createTime);
        presentRecordDao.insert(presentRecord);
        return Result.ok("下单成功");
    }

    /**
     * 提现cny
     * @param user
     * @param cny
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addCnyPutForward(User user,Integer forwardType, BigDecimal cny) {
        Integer userId = user.getUserId();
        User selectUser = userDao.selectById(userId);
        BigDecimal cnyBalance = selectUser.getCny();
        int num = cny.compareTo(cnyBalance);
        if(num == 1){
            return Result.error("请填入正确的金额");
        }
        /**
         * 更新用户余额
         */
        cnyBalance = cnyBalance.subtract(cny);
        selectUser.setCny(cnyBalance);
        userDao.updateAllColumnById(selectUser);

        String putForwardCode = SerialNumberUtil.toSerialNumber(user.getUserId());
        //生成提现单号
        String putForwardNo;
        putForwardNo = "CNY" + putForwardCode;
        Date createTime = new Date();
        PutForward putForward = new PutForward(putForwardNo,user.getUserId(),forwardType,0,cny,createTime);
        putForwardDao.insert(putForward);
        PresentRecord presentRecord = new PresentRecord(putForward.getPutForwardId(),0,createTime);
        presentRecordDao.insert(presentRecord);
        return Result.ok("下单成功");
    }
}
