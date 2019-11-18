package com.frank.order_service.service;

import com.frank.order_service.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="product-service",fallback = ProductClientFallback.class)
public interface ProductOrderClient {

    @GetMapping("/api/v1/product/find")
    public String findProductById(@RequestParam("id") Integer id);

}
