package com.stephenmeaney.services.orderlineitem.service;

import com.stephenmeaney.services.order.data.repository.OrderRepository;
import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import com.stephenmeaney.services.orderlineitem.data.repository.OrderLineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineItemService {

    private OrderLineItemRepository orderLineItemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderLineItemService(OrderLineItemRepository orderLineItemRepository, OrderRepository orderRepository) {
        this.orderLineItemRepository = orderLineItemRepository;
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<List<OrderLineItem>> getAll(long orderId) {
        if (orderRepository.findById(orderId) != null) {
            return new ResponseEntity<>(orderLineItemRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<OrderLineItem> getById(long orderLineItemId, long orderId) {
        if (orderRepository.findById(orderId) != null) {
            return new ResponseEntity<>(orderLineItemRepository.findById(orderLineItemId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<OrderLineItem> insert(OrderLineItem orderLineItem, long orderId) {
        if (orderRepository.findById(orderId) != null) {
            return new ResponseEntity<>(orderLineItemRepository.save(orderLineItem), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<OrderLineItem> update(OrderLineItem newOrderLineItem, long orderLineItemId, long orderId) {
        // check contract for "id not found" behavior and how to handle incomplete entity
        if (orderRepository.findById(orderId) != null) {
            newOrderLineItem.setOrderLineItemId(orderLineItemId);
            return new ResponseEntity<>(orderLineItemRepository.save(newOrderLineItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<OrderLineItem> delete(long orderLineItemId, long orderId) {
        if (orderRepository.findById(orderId) != null) {
            orderLineItemRepository.deleteById(orderLineItemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
