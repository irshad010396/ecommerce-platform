package com.ecom.orderservice.service;

import com.ecom.orderservice.dto.OrderDto;

public interface OrderService {
    String createOrder(OrderDto orderdto);

    OrderDto getOrderDetails(String id);

    String updateOrder(OrderDto orderDto);

    String deleteOrder(String id);
}
