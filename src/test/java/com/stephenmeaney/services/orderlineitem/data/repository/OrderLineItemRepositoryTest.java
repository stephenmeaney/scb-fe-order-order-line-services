package com.stephenmeaney.services.orderlineitem.data.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-datasets.xml")
public class OrderLineItemRepositoryTest {

    @Autowired
    OrderLineItemRepository orderLineItemRepository;

    @Test
    public void testFindAll() {
        List<OrderLineItem> orderLineItemList = orderLineItemRepository.findAll();

        assertThat(orderLineItemList.get(0).getOrderLineItemId()).isEqualTo(1L);
        assertThat(orderLineItemList.get(0).getPrice()).isEqualTo(1.11);
        assertThat(orderLineItemList.get(0).getQuantity()).isEqualTo(1);
        assertThat(orderLineItemList.get(0).getProductId()).isEqualTo(1);
        assertThat(orderLineItemList.get(0).getShipmentId()).isEqualTo(1);

        assertThat(orderLineItemList.get(1).getOrderLineItemId()).isEqualTo(2);
        assertThat(orderLineItemList.get(1).getPrice()).isEqualTo(2.22);
        assertThat(orderLineItemList.get(1).getQuantity()).isEqualTo(2);
        assertThat(orderLineItemList.get(1).getProductId()).isEqualTo(2);
        assertThat(orderLineItemList.get(1).getShipmentId()).isEqualTo(2);
    }

    @Test
    public void testFindById() {
        OrderLineItem orderLineItem = orderLineItemRepository.findById(1);

        assertThat(orderLineItem.getOrderLineItemId()).isEqualTo(1L);
        assertThat(orderLineItem.getPrice()).isEqualTo(1.11);
        assertThat(orderLineItem.getQuantity()).isEqualTo(1);
        assertThat(orderLineItem.getProductId()).isEqualTo(1);
        assertThat(orderLineItem.getShipmentId()).isEqualTo(1);
    }
}