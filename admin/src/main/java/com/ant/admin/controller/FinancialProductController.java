package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.FinancialProduct;
import com.ant.entity.Product;
import com.ant.admin.service.FinancialProductService;
import com.ant.admin.service.ProductService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * 理财产品controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/financial")
public class FinancialProductController extends AbstractController{

    @Autowired
    private ProductService productService;

    @Autowired
    private FinancialProductService financialProductService;

    /**
     * 列表
     * 理财产品
     * @param params
     * @param financialProduct
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:financial:list")
    public Result List(@RequestParam Map<String,Object> params, FinancialProduct financialProduct){
        logger.info("列表请求参数：{}", params.toString());
        EntityWrapper<Product> wrapper = new EntityWrapper<Product>();
        wrapper.like("product.product_name", financialProduct.getProductName());
        PageUtils page = new PageUtils(financialProductService.queryPage(params, wrapper));
        return Result.ok().put("page", page);
    }

    /**
     *
     * @param productId
     * @return
     */
    @RequestMapping("/info/{productId}")
    @RequiresPermissions("product:financial:info")
    public Result info(@PathVariable("productId") Integer productId){
        FinancialProduct financialProduct =  financialProductService.infoFinancial(productId);
        return Result.ok().put("financialProduct",financialProduct);
    }
    /**
     * 保存
     * 理财产品
     *
     * @param financialProduct
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:financial:save")
    public Result save(@RequestBody(required = false) FinancialProduct financialProduct){
        logger.info("保存请求参数：{}", financialProduct.toString());
        ValidatorUtils.validateEntity(financialProduct);
        financialProductService.insertProduct(financialProduct);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:financial:update")
    public Result update(@RequestBody FinancialProduct financialProduct){
        logger.info("保存请求参数：{}", financialProduct.toString());
        ValidatorUtils.validateEntity(financialProduct);
        financialProductService.updateProduct(financialProduct);
        return Result.ok();
    }

    /**
     * 删除
     * @param productIds
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:financial:delete")
    public Result delete(@RequestBody Integer[] productIds){
        financialProductService.deleteProduct(productIds);
        return Result.ok("产品已删除");
    }

    /**
     * 修改上架 下架
     *
     * @throws IOException
     */
    @RequestMapping("/updateShelve")
    @RequiresPermissions("product:financial:update")
    public Result shelve(FinancialProduct financialProduct) throws IOException {
        logger.info("请求参数：{}", financialProduct.toString());
        Product product = new Product(financialProduct);
        productService.updateById(product);
        return Result.ok();
    }

}
