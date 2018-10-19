package com.ant.admin.dao;

import com.ant.entity.Totices;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 公告mapper
 *
 * @author Billing
 * @date 2018-8-13 16:25
 */
@Component
public interface ToticesDao extends BaseMapper<Totices> {

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    List<Totices> selectToticesList(Page<Totices> page,@Param("ew") Wrapper<Totices> wrapper);

}
