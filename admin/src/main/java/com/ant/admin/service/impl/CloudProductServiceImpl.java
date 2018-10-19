package com.ant.admin.service.impl;

import com.ant.admin.common.shiro.ShiroUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.CloudProductDao;
import com.ant.admin.dao.ProductDao;
import com.ant.entity.CloudProduct;
import com.ant.entity.Product;
import com.ant.entity.SysUser;
import com.ant.entity.User;
import com.ant.admin.service.CloudProductService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:40
 */
@Service("cloudProductService")
public class CloudProductServiceImpl extends ServiceImpl<CloudProductDao,CloudProduct> implements CloudProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CloudProductDao cloudProductDao;

    /**
     * 查询
     * @param productId
     * @return
     */
    @Override
    public CloudProduct infoCloud(Integer productId){
        CloudProduct cloudProduct = cloudProductDao.selectByProductId(productId);
        return cloudProduct;
    }

    /**
     * 保存
     * @param cloudProduct
     */
    @Override
    @Transactional
    public void insertProduct(CloudProduct cloudProduct) {
        //插入产品表数据
        Product product = new Product(cloudProduct);
        //设置产品分类
        product.setCategoryId(2);
        //设置创建日期
        product.setCreateAt(new Date());
        //获取登录用户信息
        SysUser sysUser  = ShiroUtils.getUser();
        //设置插入产品的管理员id
        if(sysUser != null){
            product.setCreateUser(sysUser.getUserId());
        }
        productDao.insert(product);
        //插入云算力产品明细表数据
        cloudProduct.setProductId(product.getProductId());
        cloudProductDao.insert(cloudProduct);
    }

    /**
     * 更新
     * @param cloudProduct
     */
    @Override
    @Transactional
    public void updateProduct(CloudProduct cloudProduct) {
        Product product = new Product(cloudProduct);
        SysUser sysUser  = ShiroUtils.getUser();
        if(sysUser != null){
            product.setUpdateUser(sysUser.getUserId());
        }
        product.setUpdateAt(new Date());
        //全部更新
        productDao.updateAllColumnById(product);
        cloudProductDao.updateAllColumnById(cloudProduct);
    }

    /**
     * 删除
     * @param productIds
     */
    @Override
    public void deleteProduct(Integer[] productIds) {
        for(int i=0;i<productIds.length;i++){
            Integer productId=productIds[i];
            Product product = productDao.selectById(productId);
            CloudProduct cloudProduct = cloudProductDao.selectByProductId(productId);
            product.setDelFlag(1);
            cloudProduct.setDelFlag(1);
            productDao.updateAllColumnById(product);
            cloudProductDao.updateAllColumnById(cloudProduct);
        }
    }

    @Override
    public Page<CloudProduct> queryPage(Map<String, Object> params, Wrapper<Product> wrapper) {
        Page<CloudProduct> page =new Query<CloudProduct>(params).getPage();
        return page.setRecords(baseMapper.selectProductList(page,wrapper));
    }
}
