package com.ecom.orderservice.dto;

import com.ecom.orderservice.entity.Address;
import com.ecom.orderservice.entity.OrderItem;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private String id;
    private String userId;
    private LocalDateTime orderDate;
    private String status;
    private double totalAmount;
    private List<OrderItem> items;
    private Address shippingAddress;

    private Address billingAddress;
    private String paymentMethod;
    private String transactionId;
}
