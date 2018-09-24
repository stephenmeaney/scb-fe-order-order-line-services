package com.stephenmeaney.services.order.api;

import com.stephenmeaney.services.order.business.projection.OrderSummaryProjection;
import com.stephenmeaney.services.order.business.summary.OrderSummary;
import com.stephenmeaney.services.order.client.domain.AddressSummary;
import com.stephenmeaney.services.order.data.entity.CustomerOrder;
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

//    @GetMapping("")
//    public List<CustomerOrder> getAll() {
//        return orderService.getAll();
//    }

//    @GetMapping("/{accountId}")
//    public List<OrderSummaryProjection> getOrderSummary(@PathVariable long accountId) {
//        return orderService.getOrderSummary(accountId);
//    }

    @GetMapping("/{accountId}")
    public List<OrderSummary> getOrderSummary(@PathVariable long accountId) {
        return orderService.getOrderSummary(accountId);
    }

    @GetMapping("")
    public List<CustomerOrder> getAllForAccount(@RequestParam(value = "accountId", required = false) Long accountId) {
        if (accountId == null) {
            return orderService.getAll();
        } else {
            return orderService.getAllForAccount(accountId);
        }
    }

    @GetMapping("/order/{id}")
    public CustomerOrder getOrder(@PathVariable long id) {
        return orderService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<CustomerOrder> insert(@RequestBody CustomerOrder customerOrder) {
        return new ResponseEntity<>(orderService.insert(customerOrder), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public CustomerOrder update(@PathVariable long id, @RequestBody CustomerOrder customerOrder) {
        return orderService.update(id, customerOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerOrder> delete(@PathVariable long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
