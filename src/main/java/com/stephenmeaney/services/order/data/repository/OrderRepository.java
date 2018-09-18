package com.stephenmeaney.services.order.data.repository;

import com.stephenmeaney.services.order.data.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();

    Order findById(long id);
}
