package com.ecom.orderservice.service;

import com.ecom.orderservice.dto.OrderDto;
import com.ecom.orderservice.entity.Orders;
import com.ecom.orderservice.entity.OrderItem;
import com.ecom.orderservice.exception.ResourceNotFoundException;
import com.ecom.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String createOrder(OrderDto orderdto) throws ResourceNotFoundException {
        if (orderdto==null) {
            throw new ResourceNotFoundException("Object can not be null.");
        }
        List<OrderItem> list = new ArrayList<>();
        for (OrderItem item : orderdto.getItems()) {
            String uri ="http://localhost:10002/product/getproduct?id="+item.getProductId();
            System.out.println("***********************"+uri);
            OrderItem orderItem = restTemplate.getForObject(uri,OrderItem.class);
            if (orderItem==null) {
                throw new ResourceNotFoundException("Product with productid :"+item.getProductId()+" does not exist");
            }
            item.setProductId(orderItem.getProductId());
            item.setPrice(orderItem.getPrice());
            item.setQuantity(orderItem.getQuantity());
            list.add(orderItem);
        }
        orderdto.setItems(list);
        orderdto.setOrderDate(LocalDateTime.now());
        Orders order = Orders.ConverOrderDtoTOOrder(orderdto);
        orderRepository.save(order);
        return "Order has been crrated with orderid :" +order.getId();
    }

    @Override
    public OrderDto getOrderDetails(String id) throws ResourceNotFoundException{
        Orders order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Object can not be found"));
        return Orders.ConverOrderToOrderDto(order);
    }

    @Override
    public String updateOrder(OrderDto orderDto) throws ResourceNotFoundException{
        orderRepository.findById(orderDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Object can not be found"));
        Orders order = Orders.ConverOrderDtoTOOrder(orderDto);
        orderRepository.save(order);
        return "Order with orderid :" +order.getId() + "has been update";
    }

    @Override
    public String deleteOrder(String id) throws ResourceNotFoundException{
        orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Object can not be found"));
        orderRepository.deleteById(id);
        return "Order with orderid :" +id+ "has been deleted";
    }
}
