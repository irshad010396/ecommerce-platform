package com.ecom.orderservice.service;

import com.ecom.orderservice.dto.OrderDto;
import com.ecom.orderservice.entity.OrderItem;
import com.ecom.orderservice.entity.Orders;
import com.ecom.orderservice.exception.ResourceNotFoundException;
import com.ecom.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public String createOrder(OrderDto orderdto) throws ResourceNotFoundException {
        if (orderdto == null) {
            throw new ResourceNotFoundException("Order payload cannot be null.");
        }
        List<OrderItem> resolvedItems = new ArrayList<>();
        for (OrderItem item : orderdto.getItems()) {
            String productUri = String.format("http://PRODUCT-SERVICE/api/v1/products/%s", item.getProductId());
            OrderItem catalogItem = webClientBuilder.build()
                    .get()
                    .uri(productUri)
                    .retrieve()
                    .bodyToMono(OrderItem.class)
                    .block();
            if (catalogItem == null || catalogItem.getProductId() == null) {
                throw new ResourceNotFoundException("Product with id: " + item.getProductId() + " does not exist");
            }
            item.setPrice(catalogItem.getPrice());
            item.setQuantity(item.getQuantity() != null ? item.getQuantity() : catalogItem.getQuantity());
            resolvedItems.add(item);
        }
        orderdto.setItems(resolvedItems);
        orderdto.setOrderDate(LocalDateTime.now());
        Orders order = Orders.ConverOrderDtoTOOrder(orderdto);
        orderRepository.save(order);
        return "Order has been created with order id: " + order.getId();
    }

    @Override
    public OrderDto getOrderDetails(String id) throws ResourceNotFoundException {
        Orders order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return Orders.ConverOrderToOrderDto(order);
    }

    @Override
    public String updateOrder(OrderDto orderDto) throws ResourceNotFoundException {
        orderRepository.findById(orderDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        Orders order = Orders.ConverOrderDtoTOOrder(orderDto);
        orderRepository.save(order);
        return "Order with order id: " + order.getId() + " has been updated";
    }

    @Override
    public String deleteOrder(String id) throws ResourceNotFoundException {
        orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.deleteById(id);
        return "Order with order id: " + id + " has been deleted";
    }
}
