package com.frank.product_service.service;

import com.frank.product_service.domain.Product;

import java.util.List;

public interface ProductService {
    /**
     * 获取所有商品信息
     * @return
     */
    public List<Product> getAllProducts();

    /**
     * 通过商品id获取商品信息
     * @param id
     * @return
     */
    public Product getProductById(Integer id);
}
