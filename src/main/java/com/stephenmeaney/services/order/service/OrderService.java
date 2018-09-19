package com.stephenmeaney.services.order.service;

import com.stephenmeaney.services.order.data.entity.CustomerOrder;
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

    public CustomerOrder getById(long id) {
        return orderRepository.findById(id);
    }

    public List<CustomerOrder> getAll() {
        return orderRepository.findAll();
    }

    public CustomerOrder insert(CustomerOrder customerOrder) {
        return orderRepository.save(customerOrder);
    }

    public CustomerOrder update(long id, CustomerOrder newCustomerOrder) {
        // would normally check contract for "id not found" behavior and how to handle incomplete entity
        newCustomerOrder.setOrderId(id);
        return orderRepository.save(newCustomerOrder);
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
