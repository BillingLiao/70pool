package com.ant.webPage.controller;

import com.ant.entity.CloudProduct;
import com.ant.webPage.service.CloudProductService;
import com.ant.webPage.service.ProductService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 云算力产品controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/cloud")
@CrossOrigin
public class CloudProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CloudProductService cloudProductService;

    /**
     * 列表
     * 云算力产品
     * @return
     */
    @GetMapping("/findList")
    public Result list(){
        List<CloudProduct> cloudProductList = cloudProductService.findList();
        return Result.ok().put("cloudProductList", cloudProductList);
    }

    /**
     * 查找最新的一条产品
     * @return
     */
    @PostMapping("/findFirst")
    public Result findOne(){
        CloudProduct cloudProduct = cloudProductService.findFirst();
        return Result.ok().put("cloudProduct", cloudProduct);
    }

    /**
     * 根据id查找产品
     * @param productId
     * @return
     */
    @GetMapping("/findOne/{productId}")
    public Result info(@PathVariable("productId") Integer productId){
        CloudProduct cloudProduct =  cloudProductService.findOne(productId);
        return Result.ok().put("cloudProduct",cloudProduct);
    }
}
