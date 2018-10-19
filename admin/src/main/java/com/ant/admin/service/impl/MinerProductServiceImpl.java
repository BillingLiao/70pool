package com.ant.admin.service.impl;

import com.ant.admin.common.shiro.ShiroUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.MinerProductDao;
import com.ant.admin.dao.ProductDao;
import com.ant.entity.MinerProduct;
import com.ant.entity.Product;
import com.ant.entity.SysUser;
import com.ant.entity.User;
import com.ant.admin.service.MinerProductService;
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
@Service("minerProductService")
public class MinerProductServiceImpl extends ServiceImpl<MinerProductDao,MinerProduct> implements MinerProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private MinerProductDao minerProductDao;

    /**
     * 查询
     * @param productId
     * @return
     */
    @Override
    public MinerProduct infoMiner(Integer productId){
        MinerProduct minerProduct = minerProductDao.selectByProductId(productId);
        return minerProduct;
    }

    /**
     * 保存
     * @param minerProduct
     * @return
     */
    @Override
    public void insertProduct(MinerProduct minerProduct) {
        //插入
        Product product = new Product(minerProduct);
        product.setCategoryId(1);
        //设置创建日期
        product.setCreateAt(new Date());
        //获取登录用户信息
        SysUser sysUser  = ShiroUtils.getUser();
        //设置插入产品的管理员id
        if(sysUser != null){
            product.setCreateUser(sysUser.getUserId());
        }
        productDao.insert(product);
        minerProduct.setProductId(product.getProductId());
        minerProductDao.insert(minerProduct);
    }

    /**
     * 更新
     * @param minerProduct
     */
    @Override
    @Transactional
    public void updateProduct(MinerProduct minerProduct) {
        Product product = new Product(minerProduct);
        SysUser sysUser  = ShiroUtils.getUser();
        if(sysUser != null){
            product.setUpdateUser(sysUser.getUserId());
        }
        product.setUpdateAt(new Date());
        //全部更新
        productDao.updateAllColumnById(product);
        minerProductDao.updateAllColumnById(minerProduct);
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
            MinerProduct minerProduct = minerProductDao.selectByProductId(productId);
            product.setDelFlag(1);
            minerProduct.setDelFlag(1);
            productDao.updateAllColumnById(product);
            minerProductDao.updateAllColumnById(minerProduct);
        }
    }

    @Override
    public Page<MinerProduct> queryPage(Map<String, Object> params, Wrapper<Product> wrapper) {
        Page<MinerProduct> page =new Query<MinerProduct>(params).getPage();
        return page.setRecords(baseMapper.selectProductList(page,wrapper));
    }
}
