package com.ant.webPage.controller;

import com.ant.entity.MinerProduct;
import com.ant.webPage.service.MinerProductService;
import com.ant.webPage.service.ProductService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 矿机产品controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/miner")
@CrossOrigin
public class MinerProductController{

    @Autowired
    private ProductService productService;

    @Autowired
    private MinerProductService minerproductService;

    /**
     * 列表
     * 矿机产品
     * @return
     */
    @GetMapping("/findList")
    public Result list(){
        List<MinerProduct> minerProductList = minerproductService.findList();
        return Result.ok().put("minerProductList", minerProductList);
    }

    /**
     * 查询单条记录
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/findOne/{productId}")
    public Result info(@PathVariable("productId") Integer productId) throws Exception {
        MinerProduct minerProduct =  minerproductService.findOne(productId);
        return Result.ok().put("minerProduct",minerProduct);
    }

}
