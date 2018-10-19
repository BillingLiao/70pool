package com.ant.webPage.service;

import com.ant.entity.Totices;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface ToticesService extends IService<Totices> {

    public List<Totices> findList();

    Totices selectOneById(Integer toticesId);
}

