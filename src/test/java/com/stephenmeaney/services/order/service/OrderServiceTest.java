package com.stephenmeaney.services.order.service;

import com.stephenmeaney.services.order.client.AddressClient;
import com.stephenmeaney.services.order.client.domain.Address;
import com.stephenmeaney.services.order.data.entity.CustomerOrder;
import com.stephenmeaney.services.order.data.repository.OrderRepository;
import com.stephenmeaney.services.orderlineitem.client.ProductClient;
import com.stephenmeaney.services.orderlineitem.client.ShipmentClient;
import com.stephenmeaney.services.orderlineitem.client.domain.Product;
import com.stephenmeaney.services.orderlineitem.client.domain.Shipment;
import com.stephenmeaney.services.orderlineitem.service.OrderLineItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @MockBean
    private OrderLineItemService orderLineItemService;

    @MockBean
    private AddressClient mockAddressClient;

    @MockBean
    private ProductClient mockProductClient;

    @MockBean
    private ShipmentClient mockShipmentClient;

    @Autowired
    private OrderService orderService;

    private CustomerOrder createMockOrder(long num) {
        CustomerOrder mockOrder = new CustomerOrder();

        mockOrder.setOrderId(num);
        mockOrder.setOrderDate(LocalDate.parse("2018-01-01"));
        mockOrder.setOrderId(num);
        mockOrder.setShippingAddressId(num);

        return mockOrder;
    }

    @Test
    public void testGetById() {
        CustomerOrder mockOrder = createMockOrder(1L);

        when(mockOrderRepository.findById(anyLong())).thenReturn(mockOrder);

        CustomerOrder returnedOrder = orderService.getById(1L);

        assertThat(returnedOrder.getOrderId()).isEqualTo(1);
        assertThat(returnedOrder.getOrderDate()).isEqualTo(LocalDate.parse("2018-01-01"));
        assertThat(returnedOrder.getOrderId()).isEqualTo(1);
        assertThat(returnedOrder.getShippingAddressId()).isEqualTo(1);
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
        assertThat(returnedOrderList.get(0).getOrderId()).isEqualTo(2);
        assertThat(returnedOrderList.get(0).getShippingAddressId()).isEqualTo(2);
    }

    @Test
    public void testInsert() {
        CustomerOrder mockOrder = createMockOrder(3L);

        when(mockOrderRepository.save(any(CustomerOrder.class))).thenReturn(mockOrder);
        when(mockAddressClient.getById(anyLong(), anyLong())).thenReturn(new ResponseEntity<Address>(new Address(), HttpStatus.CREATED));

        CustomerOrder returnedOrder = orderService.insert(mockOrder);

        assertThat(returnedOrder.getOrderId()).isEqualTo(3);
        assertThat(returnedOrder.getOrderDate()).isEqualTo(LocalDate.parse("2018-01-01"));
        assertThat(returnedOrder.getOrderId()).isEqualTo(3);
        assertThat(returnedOrder.getShippingAddressId()).isEqualTo(3);
    }

    @Test
    public void testUpdate() {
        CustomerOrder mockNewOrder = new CustomerOrder();
        mockNewOrder.setAccountId(4);
        mockNewOrder.setShippingAddressId(4);

        when(mockOrderRepository.save(any(CustomerOrder.class))).then(returnsFirstArg());

        CustomerOrder updatedOrder = orderService.update(5L, mockNewOrder);

        assertThat(updatedOrder.getOrderId()).isEqualTo(5);
        assertThat(updatedOrder.getOrderDate()).isNull();
        assertThat(updatedOrder.getAccountId()).isEqualTo(4);
        assertThat(updatedOrder.getShippingAddressId()).isEqualTo(4);
    }

    @Test
    public void testDelete() {
        orderService.delete(1);
        verify(mockOrderRepository, times(1)).deleteById(anyLong());
    }
}