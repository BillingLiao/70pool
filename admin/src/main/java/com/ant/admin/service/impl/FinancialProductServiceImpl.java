package com.ant.admin.service.impl;

import com.ant.admin.common.shiro.ShiroUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.FinancialProductDao;
import com.ant.admin.dao.ProductDao;
import com.ant.entity.FinancialProduct;
import com.ant.entity.Product;
import com.ant.entity.SysUser;
import com.ant.entity.User;
import com.ant.admin.service.FinancialProductService;
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
 * 理财产品表
 *
 * @author Billing
 * @date 2018/8/13 19:40
 */
@Service("financialProductService")
public class FinancialProductServiceImpl extends ServiceImpl<FinancialProductDao,FinancialProduct> implements FinancialProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private FinancialProductDao financialProductDao;

    /**
     * 查询
     * @param productId
     * @return
     */
    @Override
    public FinancialProduct infoFinancial(Integer productId){
        FinancialProduct financialProduct = financialProductDao.selectByProductId(productId);
        return financialProduct;
    }

    /**
     * 更新
     * @param financialProduct
     */
    @Override
    @Transactional
    public void updateProduct(FinancialProduct financialProduct) {
        Product product = new Product(financialProduct);
        //SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("sysUser");
        SysUser sysUser  = ShiroUtils.getUser();
        System.out.println(sysUser + "wowo" + sysUser);
        if(sysUser != null){
            product.setUpdateUser(sysUser.getUserId());
        }
        product.setUpdateAt(new Date());
        //全部更新
        productDao.updateAllColumnById(product);
        financialProductDao.updateAllColumnById(financialProduct);
    }

    /**
     * 保存
     * @param financialProduct
     * @return
     */
    @Override
    @Transactional
    public void insertProduct(FinancialProduct financialProduct){
        //插入产品表数据
        Product product = new Product(financialProduct);
        product.setCategoryId(3);
        //设置创建日期
        product.setCreateAt(new Date());
        //获取登录用户信息
        SysUser sysUser  = ShiroUtils.getUser();
        //设置插入产品的管理员id
        if(sysUser != null){
            product.setUpdateUser(sysUser.getUserId());
        }
        productDao.insert(product);
        financialProduct.setProductId(product.getProductId());
        financialProductDao.insert(financialProduct);
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
            FinancialProduct financialProduct = financialProductDao.selectByProductId(productId);
            product.setDelFlag(1);
            financialProduct.setDelFlag(1);
            productDao.updateAllColumnById(product);
            financialProductDao.updateAllColumnById(financialProduct);
        }
    }


    @Override
    public Page<FinancialProduct> queryPage(Map<String, Object> params, Wrapper<Product> wrapper) {
        Page<FinancialProduct> page =new Query<FinancialProduct>(params).getPage();
        return page.setRecords(baseMapper.selectProductList(page,wrapper));
    }
}
