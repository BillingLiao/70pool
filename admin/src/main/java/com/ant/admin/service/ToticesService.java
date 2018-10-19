package com.ant.admin.service;

import com.ant.entity.Totices;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

public interface ToticesService extends IService<Totices> {

    Page<Totices> queryPage(Map<String,Object> params, Wrapper<Totices> wrapper);

    void insertTotices(Totices totices);

    void updateTotices(Totices totices);

    void deleteTotices(Integer[] toticesId);

    Totices infoTotices(Integer toticesId);
}

