package com.stephenmeaney.services.orderlineitem.api;

import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import com.stephenmeaney.services.orderlineitem.service.OrderLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderLineItemController {

    private OrderLineItemService orderLineItemService;

    @Autowired
    public OrderLineItemController(OrderLineItemService orderLineItemService) {
        this.orderLineItemService = orderLineItemService;
    }

    @GetMapping("/{orderId}/lines")
    public ResponseEntity<List<OrderLineItem>> getAll(@PathVariable long orderId) {

        return orderLineItemService.getAll(orderId);
    }

    @GetMapping("/{orderId}/lines/{orderLineItemId}")
    public ResponseEntity<OrderLineItem> getById(@PathVariable long orderLineItemId, @PathVariable long orderId) {

        return orderLineItemService.getById(orderLineItemId, orderId);
    }

    @PostMapping("/{orderId}/lines")
    public ResponseEntity<OrderLineItem> insert(@RequestBody OrderLineItem orderLineItem, @PathVariable long orderId) {

        return orderLineItemService.insert(orderLineItem, orderId);
    }

    @PutMapping("/{orderId}/lines/{orderLineItemId}")
    public ResponseEntity<OrderLineItem> update(@RequestBody OrderLineItem orderLineItem, @PathVariable long orderLineItemId, @PathVariable long orderId) {

        return orderLineItemService.update(orderLineItem, orderLineItemId, orderId);
    }

    @DeleteMapping("/{orderId}/lines/{orderLineItemId}")
    public ResponseEntity<OrderLineItem> delete(@PathVariable long orderLineItemId, @PathVariable long orderId) {

        return orderLineItemService.delete(orderLineItemId, orderId);
    }
}
