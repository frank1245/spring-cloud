package com.frank.product_service.controller;

import com.frank.product_service.domain.Product;
import com.frank.product_service.service.ProductService;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Value("${server.port}")
    private Integer port;

    /**
     * 获取所有商品信息
     * @return
     */
    @RequestMapping("/query_all")
    public Object queryAll(){
        return productService.getAllProducts();
    }

    @RequestMapping("/find")
    public Object findProductById(@RequestParam("id") Integer id){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("service find by id");
        Product product = productService.getProductById(id);
        Product result = new Product();
        BeanUtils.copyProperties(product,result);
        result.setName(product.getName()+"data from "+port);
        return result;
    }
}
