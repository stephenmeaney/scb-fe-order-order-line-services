package com.stephenmeaney.services.order.data.repository;

import com.stephenmeaney.services.order.business.projection.OrderSummaryProjection;
import com.stephenmeaney.services.order.data.entity.CustomerOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {

    List<CustomerOrder> findAll();

    CustomerOrder findById(long id);

    List<CustomerOrder> findAllByAccountId(long accountId);

    List<CustomerOrder> findAllByAccountIdOrderByOrderDate(long accountId);

    List<OrderSummaryProjection> findAllProjectedByAccountId(long id);

}
