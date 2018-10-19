package com.ant.webPage.service.impl;

import com.ant.entity.Product;
import com.ant.webPage.dao.*;
import com.ant.webPage.service.ProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

}
