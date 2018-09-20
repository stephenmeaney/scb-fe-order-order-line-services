package com.stephenmeaney.services.order.data.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stephenmeaney.services.order.data.entity.CustomerOrder;
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

import java.time.LocalDate;
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
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testFindAll() throws Exception {
        List<CustomerOrder> orderList = orderRepository.findAll();

        assertThat(orderList.get(0).getOrderId()).isEqualTo(1);
        assertThat(orderList.get(0).getOrderDate()).isEqualTo(LocalDate.parse("2018-01-01"));
        assertThat(orderList.get(0).getAccountId()).isEqualTo(1);
        assertThat(orderList.get(0).getAddressId()).isEqualTo(1);

        assertThat(orderList.get(1).getOrderId()).isEqualTo(2);
        assertThat(orderList.get(1).getOrderDate()).isEqualTo(LocalDate.parse("2018-01-02"));
        assertThat(orderList.get(1).getAccountId()).isEqualTo(2);
        assertThat(orderList.get(1).getAddressId()).isEqualTo(2);
    }

    @Test
    public void testFindById() {
        CustomerOrder order = orderRepository.findById(1L);

        assertThat(order.getOrderId()).isEqualTo(1);
        assertThat(order.getOrderDate()).isEqualTo(LocalDate.parse("2018-01-01"));
        assertThat(order.getAccountId()).isEqualTo(1);
        assertThat(order.getAddressId()).isEqualTo(1);
    }


}