package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.OrderDao;
import com.ant.admin.dao.ProductDao;
import com.ant.admin.dao.PutForwardDao;
import com.ant.admin.service.OrderService;
import com.ant.admin.service.PutForwardService;
import com.ant.entity.Order;
import com.ant.entity.Product;
import com.ant.entity.PutForward;
import com.ant.entity.User;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("putForwardService")
public class PutForwardImpl extends ServiceImpl<PutForwardDao, PutForward> implements PutForwardService {

    @Autowired
    private PutForwardDao putForwardDao;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String forwardNo=(String)params.get("forwardNo");
        String forwardStatus=(String)params.get("forwardStatus");
        EntityWrapper<PutForward> putForwardEntityWrapper =new EntityWrapper<PutForward>();

        putForwardEntityWrapper.like("p.forward_no", forwardNo);
        putForwardEntityWrapper.eq(StringUtils.isNotBlank(forwardStatus), "p.forward_status", forwardStatus);
        Page<PutForward> page =new Query<PutForward>(params).getPage();
        page.setRecords(baseMapper.selectPutForwardList(page,putForwardEntityWrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

}
