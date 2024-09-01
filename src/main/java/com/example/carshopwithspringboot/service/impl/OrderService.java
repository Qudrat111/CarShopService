package com.example.carshopwithspringboot.service.impl;

import com.example.carshopwithspringboot.dto.OrderDto;
import com.example.carshopwithspringboot.model.Order;
import com.example.carshopwithspringboot.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
    }


    public Order add(OrderDto orderDto) throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(orderDto);
        Order order = objectMapper.readValue(s, Order.class);
        return orderRepository.save(order);
    }

    public Order update(OrderDto orderDto) throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(orderDto);
        Order order = objectMapper.readValue(s, Order.class);
        return orderRepository.save(order);
    }

    public Integer delete(Integer orderId) {
        orderRepository.deleteById(orderId);
        return orderId;
    }

    public Optional<Order> get(Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        return Optional.ofNullable(byId.get());

    }

    public List<Order> findAll() {
        List<Order> all = orderRepository.findAll();
        return all;
    }

}
