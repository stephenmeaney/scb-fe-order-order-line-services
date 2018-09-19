package com.stephenmeaney.services.order.service;

import com.stephenmeaney.services.order.data.entity.CustomerOrder;
import com.stephenmeaney.services.order.data.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderService.class})
public class OrderServiceTest {

    @MockBean
    private OrderRepository mockOrderRepository;

    @Autowired
    private OrderService orderService;

    private CustomerOrder createMockOrder(long num) {
        CustomerOrder mockOrder = new CustomerOrder();

        mockOrder.setOrderId(num);
        mockOrder.setOrderDate(LocalDate.parse("2018-01-01"));
        mockOrder.setDeliveryDate(LocalDate.parse("2018-02-02"));
        mockOrder.setOrderId(num);
        mockOrder.setAddressId(num);

        return mockOrder;
    }

    @Test
    public void testGetById() {
        CustomerOrder mockOrder = createMockOrder(1L);

        when(mockOrderRepository.findById(anyLong())).thenReturn(mockOrder);

        CustomerOrder returnedOrder = orderService.getById(1L);

        assertThat(returnedOrder.getOrderId()).isEqualTo(1);
        assertThat(returnedOrder.getOrderDate()).isEqualTo(LocalDate.parse("2018-01-01"));
        assertThat(returnedOrder.getDeliveryDate()).isEqualTo(LocalDate.parse("2018-02-02"));
        assertThat(returnedOrder.getOrderId()).isEqualTo(1);
        assertThat(returnedOrder.getAddressId()).isEqualTo(1);
    }

    @Test
    public void testGetAll() {
        CustomerOrder mockCustomerOrder = createMockOrder(2L);
        List<CustomerOrder> mockCustomerOrderList = new ArrayList<>();
        mockCustomerOrderList.add(mockCustomerOrder);

        when(mockOrderRepository.findAll()).thenReturn(mockCustomerOrderList);

        List<CustomerOrder> returnedOrderList = orderService.getAll();

        assertThat(returnedOrderList.get(0).getOrderId()).isEqualTo(2);
        assertThat(returnedOrderList.get(0).getOrderDate()).isEqualTo(LocalDate.parse("2018-01-01"));
        assertThat(returnedOrderList.get(0).getDeliveryDate()).isEqualTo(LocalDate.parse("2018-02-02"));
        assertThat(returnedOrderList.get(0).getOrderId()).isEqualTo(2);
        assertThat(returnedOrderList.get(0).getAddressId()).isEqualTo(2);
    }

    @Test
    public void testInsert() {
        CustomerOrder mockOrder = createMockOrder(3L);

        when(mockOrderRepository.save(any(CustomerOrder.class))).thenReturn(mockOrder);

        CustomerOrder returnedOrder = orderService.insert(mockOrder);

        assertThat(returnedOrder.getOrderId()).isEqualTo(3);
        assertThat(returnedOrder.getOrderDate()).isEqualTo(LocalDate.parse("2018-01-01"));
        assertThat(returnedOrder.getDeliveryDate()).isEqualTo(LocalDate.parse("2018-02-02"));
        assertThat(returnedOrder.getOrderId()).isEqualTo(3);
        assertThat(returnedOrder.getAddressId()).isEqualTo(3);
    }

    @Test
    public void testUpdate() {
        CustomerOrder mockNewOrder = new CustomerOrder();
        mockNewOrder.setAccountId(4);
        mockNewOrder.setAddressId(4);

        when(mockOrderRepository.save(any(CustomerOrder.class))).then(returnsFirstArg());

        CustomerOrder updatedOrder = orderService.update(5L, mockNewOrder);

        assertThat(updatedOrder.getOrderId()).isEqualTo(5);
        assertThat(updatedOrder.getOrderDate()).isNull();
        assertThat(updatedOrder.getDeliveryDate()).isNull();
        assertThat(updatedOrder.getAccountId()).isEqualTo(4);
        assertThat(updatedOrder.getAddressId()).isEqualTo(4);
    }

    @Test
    public void testDelete() {
        orderService.delete(1);
        verify(mockOrderRepository, times(1)).deleteById(anyLong());
    }
}