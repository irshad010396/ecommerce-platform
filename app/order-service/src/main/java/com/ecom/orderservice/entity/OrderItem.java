package com.ecom.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
//@Entity
public class OrderItem {
//    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(insertable=false, updatable=false)
//    private Long id;

//    @Column(name = "product_id", nullable = false)
    private String productId;

//    @Column(name = "quantity", nullable = false)
    private Integer quantity;

//    @Column(name = "price", nullable = false)
    private Double price;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    private Orders order;

}
