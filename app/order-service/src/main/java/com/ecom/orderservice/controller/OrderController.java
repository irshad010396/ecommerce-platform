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
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);


    @PostMapping("createorder")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDto orderdto) {
        log.info("Request received for creating order for user" +orderdto.getUserId());
        String message = orderService.createOrder(orderdto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("getOrderDetails")
    public ResponseEntity<OrderDto> getOrderDeatils(@RequestParam String id) {
        log.info("request received for fetching order details with id :"+id);
        OrderDto orderDto = orderService.getOrderDetails(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PutMapping("updateorder")
    public ResponseEntity<String> updateOrder(OrderDto orderDto) {
        log.info("request received for updating order with order id :"+orderDto.getId());
        String message = orderService.updateOrder(orderDto);
        return new ResponseEntity<>(message , HttpStatus.OK);
    }

    @DeleteMapping("deleteorder")
    public ResponseEntity<String> deleteOrder(@RequestParam String id) {
        log.info("request received for deleting order with order id :"+id);
        String message = orderService.deleteOrder(id);
        return new ResponseEntity<>(message , HttpStatus.OK);
    }
}
