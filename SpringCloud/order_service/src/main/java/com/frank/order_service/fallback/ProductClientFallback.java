package com.frank.order_service.fallback;

import com.frank.order_service.service.ProductOrderClient;
import org.springframework.stereotype.Component;

/**
 * 针对商品服务做降级处理
 */
@Component
public class ProductClientFallback implements ProductOrderClient {
    @Override
    public String findProductById(Integer id) {
        System.out.println("feign 调用 product-service findById 异常");
        return null;
    }
}
