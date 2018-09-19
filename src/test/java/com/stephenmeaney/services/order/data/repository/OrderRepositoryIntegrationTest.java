package com.stephenmeaney.services.order.data.repository;

import com.stephenmeaney.services.order.data.entity.CustomerOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testPersistData() {
        CustomerOrder mockOrder = new CustomerOrder();

        mockOrder.setOrderDate(LocalDate.parse("2018-01-01"));
        mockOrder.setDeliveryDate(LocalDate.parse("2018-02-02"));
        mockOrder.setAccountId(2L);
        mockOrder.setAddressId(3L);

        entityManager.persistAndFlush(mockOrder);

        CustomerOrder foundOrder = orderRepository.findById(1L);

        assertThat(foundOrder.getOrderId()).isEqualTo(1);
        assertThat(foundOrder.getOrderDate()).isEqualTo(LocalDate.parse("2018-01-01"));
        assertThat(foundOrder.getDeliveryDate()).isEqualTo(LocalDate.parse("2018-02-02"));
        assertThat(foundOrder.getAccountId()).isEqualTo(2);
        assertThat(foundOrder.getAddressId()).isEqualTo(3);
    }
}
