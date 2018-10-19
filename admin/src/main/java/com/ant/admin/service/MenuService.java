package com.ant.admin.service;

import com.ant.entity.Menu;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/16 16:25
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<Menu> queryListParentId(Integer parentId, List<Integer> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<Menu> queryListParentId(Integer parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<Menu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    @Cacheable("sysMenu")
    List<Menu> getUserMenuList(Integer userId);

    /**
     * 删除
     */
    void delete(Integer menuId);
}
