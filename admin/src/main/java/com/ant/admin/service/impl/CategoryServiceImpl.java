package com.ant.admin.service.impl;

import com.ant.admin.dao.CategoryDao;
import com.ant.entity.Category;
import com.ant.admin.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Billing
 * @date 2018/8/13 19:17
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao,Category> implements CategoryService {
}
