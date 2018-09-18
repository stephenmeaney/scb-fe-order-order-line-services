package com.stephenmeaney.services.order.api;

import com.stephenmeaney.services.order.data.entity.Order;
import com.stephenmeaney.services.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable long id) {
        return orderService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<Order> insert(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.insert(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable long id, @RequestBody Order order) {
        return orderService.update(id, order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
