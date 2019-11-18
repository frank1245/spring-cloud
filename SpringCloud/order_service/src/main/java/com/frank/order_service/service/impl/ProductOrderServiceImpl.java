package com.frank.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.frank.order_service.domain.Order;
import com.frank.order_service.service.ProductOrderClient;
import com.frank.order_service.service.ProductOrderService;
import com.frank.order_service.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    @Autowired
    private ProductOrderClient productOrderClient;


    @Override
    public Order save(Integer userId, Integer productId) {
        String response = productOrderClient.findProductById(productId);
        JsonNode jsonNode = JsonUtils.str2JsonNode(response);
        System.out.println(jsonNode);
        Order order = new Order();
        order.setId(Integer.parseInt(jsonNode.get("id").toString()));
        order.setProductName(jsonNode.get("name").toString());
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setTradeNo(UUID.randomUUID().toString());
        order.setPrice(Double.parseDouble(jsonNode.get("price").toString()));
        return order;
    }
}
