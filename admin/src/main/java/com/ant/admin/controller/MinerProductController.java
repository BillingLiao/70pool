package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.MinerProduct;
import com.ant.entity.Product;
import com.ant.admin.service.MinerProductService;
import com.ant.admin.service.ProductService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 矿机产品controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/miner")
public class MinerProductController extends AbstractController{

    @Autowired
    private ProductService productService;

    @Autowired
    private MinerProductService minerproductService;

    /**
     * 列表
     * 理财产品
     * @param params
     * @param minerProduct
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:miner:list")
    public Result list(@RequestParam Map<String,Object> params, MinerProduct minerProduct){
        EntityWrapper<Product> wrapper = new EntityWrapper<Product>();
        wrapper.like("product.product_name", minerProduct.getProductName());
        PageUtils page = new PageUtils(minerproductService.queryPage(params, wrapper));
        return Result.ok().put("page", page);
    }

    @RequestMapping("/info/{productId}")
    @RequiresPermissions("product:miner:info")
    public Result info(@PathVariable("productId") Integer productId) throws Exception {
        MinerProduct minerProduct =  minerproductService.infoMiner(productId);
        return Result.ok().put("minerProduct",minerProduct);
    }
    /**
     * 保存
     * 理财产品
     *
     * @param minerProduct
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:miner:save")
    public Result save(@RequestBody(required = false) MinerProduct minerProduct){
        ValidatorUtils.validateEntity(minerProduct);
        minerproductService.insertProduct(minerProduct);
        return Result.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:miner:update")
    public Result update(@RequestBody MinerProduct minerProduct){
        ValidatorUtils.validateEntity(minerProduct);
        minerproductService.updateProduct(minerProduct);
        return Result.ok();
    }

    /**
     * 删除
     * @param productIds
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:miner:delete")
    public Result delete(@RequestBody Integer[] productIds){
        minerproductService.deleteProduct(productIds);
        return Result.ok("产品已删除");
    }

    /**
     * 修改上架 下架
     */
    @RequestMapping("/updateShelve")
    @RequiresPermissions("product:miner:update")
    public Result shelve(MinerProduct minerProduct){
        Product product = new Product(minerProduct);
        productService.updateById(product);
        return Result.ok();
    }

}
