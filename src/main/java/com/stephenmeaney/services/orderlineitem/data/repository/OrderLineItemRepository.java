package com.stephenmeaney.services.orderlineitem.data.repository;

import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineItemRepository extends CrudRepository<OrderLineItem, Long> {

    List<OrderLineItem> findAll();

    OrderLineItem findById(long id);
}
