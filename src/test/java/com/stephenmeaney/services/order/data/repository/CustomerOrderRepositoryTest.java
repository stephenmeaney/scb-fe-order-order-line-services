package com.stephenmeaney.services.order.data.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-datasets.xml")
public class CustomerOrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testFindAll() throws Exception {


//        List<CustomerOrder> orderList = orderRepository.findAll();
//
//        assertThat(orderList.get(0).getOrderId()).isEqualTo(1);
//        assertThat(orderList.get(0).getOrderDate()).isEqualTo(LocalDateTime.parse("2018-01-01T01:01:01"));
//        assertThat(orderList.get(0).getAccountId()).isEqualTo(1);
//        assertThat(orderList.get(0).getAddressId()).isEqualTo(1);


    }
}