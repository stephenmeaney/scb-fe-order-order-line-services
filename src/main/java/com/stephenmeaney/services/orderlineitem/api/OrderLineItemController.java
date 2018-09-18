package com.stephenmeaney.services.orderlineitem.api;

import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import com.stephenmeaney.services.orderlineitem.service.OrderLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderlineitems")
public class OrderLineItemController {

    private OrderLineItemService orderLineItemService;

    @Autowired
    public OrderLineItemController(OrderLineItemService orderLineItemService) {
        this.orderLineItemService = orderLineItemService;
    }

    @GetMapping("")
    public List<OrderLineItem> getAll() {
        return orderLineItemService.getAll();
    }

    @GetMapping("/{id}")
    public OrderLineItem getById(@PathVariable long id) {
        return orderLineItemService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<OrderLineItem> insert(@RequestBody OrderLineItem orderLineItem) {
        return new ResponseEntity<>(orderLineItemService.insert(orderLineItem), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public OrderLineItem update(@PathVariable long id, @RequestBody OrderLineItem orderLineItem) {
        return orderLineItemService.update(id, orderLineItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderLineItem> delete(@PathVariable long id) {
        orderLineItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
