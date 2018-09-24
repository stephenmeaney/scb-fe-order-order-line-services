package com.stephenmeaney.services.orderlineitem.data.repository;

import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class OrderLineItemRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderLineItemRepository orderLineItemRepository;

    @Test
    public void testPersistData() {
        OrderLineItem mockOrderLineItem = new OrderLineItem();
        mockOrderLineItem.setPrice(2.22);
        mockOrderLineItem.setQuantity(3);
        mockOrderLineItem.setProductId(4);
        mockOrderLineItem.setShipmentId(5);

        if (orderLineItemRepository.findById(1L) != null) {
            orderLineItemRepository.deleteById(1L);
        }

        entityManager.persistAndFlush(mockOrderLineItem);

        OrderLineItem foundOrderLineItem = orderLineItemRepository.findById(1);

        assertThat(foundOrderLineItem.getOrderLineItemId()).isEqualTo(1);
        assertThat(foundOrderLineItem.getPrice()).isEqualTo(2.22);
        assertThat(foundOrderLineItem.getQuantity()).isEqualTo(3);
        assertThat(foundOrderLineItem.getProductId()).isEqualTo(4);
        assertThat(foundOrderLineItem.getShipmentId()).isEqualTo(5);
    }
}
