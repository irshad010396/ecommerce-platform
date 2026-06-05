package com.ecom.orderservice.controller;

import com.ecom.orderservice.dto.OrderDto;
import com.ecom.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);


    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDto orderdto) {
        log.info("Request received for creating order for user={}", orderdto.getUserId());
        String message = orderService.createOrder(orderdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderDeatils(@PathVariable String id) {
        log.info("Request received for fetching order details with id={}", id);
        OrderDto orderDto = orderService.getOrderDetails(id);
        return ResponseEntity.ok(orderDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable String id, @RequestBody OrderDto orderDto) {
        log.info("Request received for updating order with order id={}", id);
        orderDto.setId(id);
        String message = orderService.updateOrder(orderDto);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        log.info("Request received for deleting order with order id={}", id);
        String message = orderService.deleteOrder(id);
        return ResponseEntity.ok(message);
    }
}
