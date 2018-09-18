package com.stephenmeaney.services.orderlineitem.service;

import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import com.stephenmeaney.services.orderlineitem.data.repository.OrderLineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineItemService {

    private OrderLineItemRepository orderLineItemRepository;

    @Autowired
    public OrderLineItemService(OrderLineItemRepository orderLineItemRepository) {
        this.orderLineItemRepository = orderLineItemRepository;
    }

    public OrderLineItem getById(long id) {
        return orderLineItemRepository.findById(id);
    }

    public List<OrderLineItem> getAll() {
        return orderLineItemRepository.findAll();
    }

    public OrderLineItem insert(OrderLineItem orderLineItem) {
        return orderLineItemRepository.save(orderLineItem);
    }

    public OrderLineItem update(long id, OrderLineItem newOrderLineItem) {
        // would normally check contract for "id not found" behavior and how to handle incomplete entity
        newOrderLineItem.setOrderLineItemId(id);
        return orderLineItemRepository.save(newOrderLineItem);
    }

    public void delete(long id) {
        orderLineItemRepository.deleteById(id);
    }
}
