package com.stephenmeaney.services.orderlineitem.service;

import com.stephenmeaney.services.order.data.entity.CustomerOrder;
import com.stephenmeaney.services.order.data.repository.OrderRepository;
import com.stephenmeaney.services.orderlineitem.client.ProductClient;
import com.stephenmeaney.services.orderlineitem.client.ShipmentClient;
import com.stephenmeaney.services.orderlineitem.client.domain.Product;
import com.stephenmeaney.services.orderlineitem.client.domain.Shipment;
import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import com.stephenmeaney.services.orderlineitem.data.repository.OrderLineItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.security.auth.login.AccountException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderLineItemService.class})
public class OrderLineItemServiceTest {

    @MockBean
    private OrderLineItemRepository mockOrderLineItemRepository;

    @MockBean
    private OrderRepository mockOrderRepository;

    @MockBean
    private ProductClient mockProductClient;

    @MockBean
    private ShipmentClient mockShipmentClient;

    @Autowired
    private OrderLineItemService orderLineItemService;

    private OrderLineItem createMockOrderLineItem(long num) {
        OrderLineItem mockOrderLineItem = new OrderLineItem();
        mockOrderLineItem.setOrderLineItemId(num);
        mockOrderLineItem.setPrice(num + 0.01);
        mockOrderLineItem.setQuantity((int) num);
        mockOrderLineItem.setProductId(num);
        mockOrderLineItem.setShipmentId(num);

        return mockOrderLineItem;
    }

    @Test
    public void testGetById() {
        OrderLineItem mockOrderLineItem = createMockOrderLineItem(1L);

        when(mockOrderRepository.findById(anyLong())).thenReturn(new CustomerOrder());
        when(mockOrderLineItemRepository.findById(anyLong())).thenReturn(mockOrderLineItem);

        OrderLineItem returnedOrderLineItem = orderLineItemService.getById(1L, 1L).getBody();

        assertThat(returnedOrderLineItem.getOrderLineItemId()).isEqualTo(1);
        assertThat(returnedOrderLineItem.getPrice()).isEqualTo(1.01);
        assertThat(returnedOrderLineItem.getQuantity()).isEqualTo(1);
        assertThat(returnedOrderLineItem.getProductId()).isEqualTo(1);
        assertThat(returnedOrderLineItem.getShipmentId()).isEqualTo(1);
    }

    @Test
    public void testGetById_orderDoesNotExist() {
        when(mockOrderRepository.findById(anyLong())).thenReturn(null);
        when(mockOrderLineItemRepository.findById(anyLong())).thenReturn(new OrderLineItem());

        OrderLineItem returnedOrderLineItem = orderLineItemService.getById(1L, 1L).getBody();

        assertThat(returnedOrderLineItem).isNull();
    }

    @Test
    public void testGetById_orderLineItemDoesNotExist() {
        when(mockOrderRepository.findById(anyLong())).thenReturn(new CustomerOrder());
        when(mockOrderLineItemRepository.findById(anyLong())).thenReturn(null);

        OrderLineItem returnedOrderLineItem = orderLineItemService.getById(1L, 1L).getBody();

        assertThat(returnedOrderLineItem).isNull();
    }

    @Test
    public void testGetAll() {
        OrderLineItem mockOrderLineItem = createMockOrderLineItem(2L);
        List<OrderLineItem> mockOrderLineItemList = new ArrayList<>();
        mockOrderLineItemList.add(mockOrderLineItem);

        when(mockOrderRepository.findById(anyLong())).thenReturn(new CustomerOrder());
        when(mockOrderLineItemRepository.findAll()).thenReturn(mockOrderLineItemList);

        List<OrderLineItem> returnedOrderLineItemList = orderLineItemService.getAll(2L).getBody();

        assertThat(returnedOrderLineItemList.get(0).getOrderLineItemId()).isEqualTo(2);
        assertThat(returnedOrderLineItemList.get(0).getPrice()).isEqualTo(2.01);
        assertThat(returnedOrderLineItemList.get(0).getQuantity()).isEqualTo(2);
        assertThat(returnedOrderLineItemList.get(0).getProductId()).isEqualTo(2);
        assertThat(returnedOrderLineItemList.get(0).getShipmentId()).isEqualTo(2);
    }

    @Test
    public void testGetAll_orderDoesNotExist() {
        when(mockOrderRepository.findById(anyLong())).thenReturn(null);
        when(mockOrderLineItemRepository.findAll()).thenReturn(new ArrayList<OrderLineItem>());

        OrderLineItem returnedOrderLineItem = orderLineItemService.getById(1L, 1L).getBody();

        assertThat(returnedOrderLineItem).isNull();
    }

    @Test
    public void testGetAll_orderLineItemDoesNotExist() {
        when(mockOrderRepository.findById(anyLong())).thenReturn(new CustomerOrder());
        when(mockOrderLineItemRepository.findAll()).thenReturn(null);

        OrderLineItem returnedOrderLineItem = orderLineItemService.getById(1L, 1L).getBody();

        assertThat(returnedOrderLineItem).isNull();
    }

    @Test
    public void testInsert() {
        OrderLineItem mockOrderLineItem = createMockOrderLineItem(3L);

        when(mockOrderRepository.findById(anyLong())).thenReturn(new CustomerOrder());
        when(mockProductClient.getById(anyLong())).thenReturn(new Product());
        when(mockShipmentClient.getById(anyLong())).thenReturn(new Shipment());

        when(mockOrderLineItemRepository.save(any(OrderLineItem.class))).thenReturn(mockOrderLineItem);

        OrderLineItem returnedOrderLineItem = orderLineItemService.insert(mockOrderLineItem, 1L).getBody();

        assertThat(returnedOrderLineItem.getOrderLineItemId()).isEqualTo(3);
        assertThat(returnedOrderLineItem.getPrice()).isEqualTo(3.01);
        assertThat(returnedOrderLineItem.getQuantity()).isEqualTo(3);
        assertThat(returnedOrderLineItem.getProductId()).isEqualTo(3);
        assertThat(returnedOrderLineItem.getShipmentId()).isEqualTo(3);
    }

    @Test
    public void testInsert_orderDoesNotExist() {
        when(mockOrderRepository.findById(anyLong())).thenReturn(null);
        when(mockOrderLineItemRepository.save(any(OrderLineItem.class))).thenReturn(new OrderLineItem());

        OrderLineItem returnedOrderLineItem = orderLineItemService.insert(new OrderLineItem(), 1L).getBody();

        assertThat(returnedOrderLineItem).isNull();
    }

    @Test
    public void testUpdate() {
        OrderLineItem mockNewOrderLineItem = new OrderLineItem();
        mockNewOrderLineItem.setQuantity(4);
        mockNewOrderLineItem.setPrice(4.44);

        when(mockOrderRepository.findById(anyLong())).thenReturn(new CustomerOrder());
        when(mockOrderLineItemRepository.save(any(OrderLineItem.class))).then(returnsFirstArg());

        OrderLineItem updatedOrderLineItem = orderLineItemService.update(mockNewOrderLineItem, 5L, 1L).getBody();

        assertThat(updatedOrderLineItem.getOrderLineItemId()).isEqualTo(5);
        assertThat(updatedOrderLineItem.getPrice()).isEqualTo(4.44);
        assertThat(updatedOrderLineItem.getQuantity()).isEqualTo(4);
        assertThat(updatedOrderLineItem.getProductId()).isEqualTo(0);
        assertThat(updatedOrderLineItem.getShipmentId()).isEqualTo(0);
    }

    @Test
    public void testUpdate_orderDoesNotExist() {
        OrderLineItem mockOrderLineItem = new OrderLineItem();

        when(mockOrderRepository.findById(anyLong())).thenReturn(null);
        when(mockOrderLineItemRepository.save(any(OrderLineItem.class))).then(returnsFirstArg());

        OrderLineItem updatedOrderLineItem = orderLineItemService.update(mockOrderLineItem, 5L, 1L).getBody();

        assertThat(updatedOrderLineItem).isNull();
    }

    @Test
    public void testDelete() {
        when(mockOrderRepository.findById(anyLong())).thenReturn(new CustomerOrder());

        ResponseEntity<OrderLineItem> responseEntity =  orderLineItemService.delete(1L, 1L);

        assertThat(responseEntity.getBody()).isNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void testDelete_orderDoesNotExist() {
        when(mockOrderRepository.findById(anyLong())).thenReturn(null);

        ResponseEntity<OrderLineItem> responseEntity =  orderLineItemService.delete(1L, 1L);

        assertThat(responseEntity.getBody()).isNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}