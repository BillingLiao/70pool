package com.ant.admin.service.impl;

import com.ant.admin.dao.ProductDao;
import com.ant.admin.service.ProductService;
import com.ant.entity.Product;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 理财产品表
 *
 * @author Billing
 * @date 2018/8/13 19:40
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao,Product> implements ProductService {

}
