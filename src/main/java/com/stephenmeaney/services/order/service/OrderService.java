package com.stephenmeaney.services.order.service;

import com.stephenmeaney.services.order.data.entity.Order;
import com.stephenmeaney.services.order.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getById(long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order insert(Order order) {
        return orderRepository.save(order);
    }

    public Order update(long id, Order newOrder) {
        // would normally check contract for "id not found" behavior and how to handle incomplete entity
        newOrder.setOrderId(id);
        return orderRepository.save(newOrder);
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
