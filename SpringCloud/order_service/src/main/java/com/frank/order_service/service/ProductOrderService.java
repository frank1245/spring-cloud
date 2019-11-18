package com.frank.order_service.service;

import com.frank.order_service.domain.Order;


import java.util.List;

public interface ProductOrderService {

    /**
     * 订单业务下单接口
     */
    public Order save(Integer userId,Integer productId);
}
