package com.ecom.orderservice.entity;

import com.ecom.orderservice.dto.OrderDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
//@Entity
@Document
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
//    @Column(name = "user_id" , nullable = false)
    private String userId;
//    @Column(name = "order_date" , nullable = false)
    private LocalDateTime orderDate;
//    @Column(name = "order_status" , nullable = false)
    private String status;
//    @Column(name = "total_amount" , nullable = false)
    private Double totalAmount;
//    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;
//    @Embedded
    private Address shippingAddress;
//    @Embedded
//    @AttributeOverrides(
//            {
//                    @AttributeOverride(name = "street" , column = @Column(name = "billing_street")),
//                    @AttributeOverride(name = "city" , column = @Column(name = "billing_city")),
//                    @AttributeOverride(name = "state" , column = @Column(name = "billing_state")),
//                    @AttributeOverride(name = "zip" , column = @Column(name = "billing_zip")),
//                    @AttributeOverride(name = "country" , column = @Column(name = "billing_country"))
//            }
//    )
    private Address billingAddress;
//    @Column(name = "payment_method" , nullable = false)
    private String paymentMethod;
//    @Column(name = "transaction_id" , nullable = false , unique = true)
    private String transactionId;


    public static Orders ConverOrderDtoTOOrder(OrderDto orderDto) {
        Orders order = new Orders();
        order.setId(orderDto.getId());
        order.setOrderDate(orderDto.getOrderDate());
        order.setItems(orderDto.getItems());
        order.setStatus(orderDto.getStatus());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setBillingAddress(orderDto.getBillingAddress());
        order.setShippingAddress(orderDto.getShippingAddress());
        order.setTransactionId(orderDto.getTransactionId());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setUserId(orderDto.getUserId());
        return order;
    }

    public static OrderDto ConverOrderToOrderDto(Orders order) {
        OrderDto orderdto = new OrderDto();
        orderdto.setId(order.getId());
        orderdto.setOrderDate(order.getOrderDate());
        orderdto.setItems(order.getItems());
        orderdto.setStatus(order.getStatus());
        orderdto.setPaymentMethod(order.getPaymentMethod());
        orderdto.setBillingAddress(order.getBillingAddress());
        orderdto.setShippingAddress(order.getShippingAddress());
        orderdto.setTransactionId(order.getTransactionId());
        orderdto.setTotalAmount(order.getTotalAmount());
        orderdto.setUserId(order.getUserId());
        return orderdto;
    }
}
