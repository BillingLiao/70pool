package com.ant.admin.dao;

import com.ant.entity.Order;
import com.ant.entity.PutForward;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:22
 */
public interface PutForwardDao extends BaseMapper<PutForward> {

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    List<PutForward> selectPutForwardList(Page<PutForward> page, @Param("ew") Wrapper<PutForward> wrapper);

}
