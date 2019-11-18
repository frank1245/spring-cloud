package com.frank.product_service.service.impl;

import com.frank.product_service.domain.Product;
import com.frank.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private static Map<Integer,Product> map = new HashMap<>();
    static{
        Product p1 = new Product(1,"电冰箱",2999.00,30);
        Product p2 = new Product(2,"电视机",1999.00,10);
        Product p3 = new Product(3,"iPhone X",5999.00,3000);
        Product p4 = new Product(4,"剃须刀",199.99,200);
        Product p5 = new Product(5,"旅行箱",300.00,10);
        Product p6 = new Product(6,"洗脸盆",30.00,50000);
        map.put(p1.getId(),p1);
        map.put(p2.getId(),p2);
        map.put(p3.getId(),p3);
        map.put(p4.getId(),p4);
        map.put(p5.getId(),p5);
        map.put(p6.getId(),p6);
    }
    @Override
    public List<Product> getAllProducts() {
        Collection<Product> collection = map.values();
        List<Product> list = new ArrayList<>(collection);
        return list;
    }

    @Override
    public Product getProductById(Integer id) {
        return map.get(id);
    }
}
