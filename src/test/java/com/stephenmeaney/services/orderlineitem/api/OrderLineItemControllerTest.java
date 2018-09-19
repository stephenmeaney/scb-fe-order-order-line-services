package com.stephenmeaney.services.orderlineitem.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import com.stephenmeaney.services.orderlineitem.service.OrderLineItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderLineItemController.class, secure = false)
public class OrderLineItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderLineItemService mockOrderLineItemService;

    @Autowired
    private ObjectMapper mapper;

    private OrderLineItem createMockOrderLineItem(long num) {
        OrderLineItem mockOrderLineItem = new OrderLineItem();
        mockOrderLineItem.setOrderLineItemId(num);
        mockOrderLineItem.setPrice(num + 0.02);
        mockOrderLineItem.setQuantity((int) num);
        mockOrderLineItem.setProductId(num);
        mockOrderLineItem.setShipmentId(num);

        return mockOrderLineItem;
    }

    @Test
    public void testGetAll() throws Exception {

        List<OrderLineItem> mockOrderLineItemList = new ArrayList<>();
        mockOrderLineItemList.add(createMockOrderLineItem(1));
        mockOrderLineItemList.add(createMockOrderLineItem(2));

        when(mockOrderLineItemService.getAll(1)).thenReturn(new ResponseEntity<>(mockOrderLineItemList, HttpStatus.OK));

        mockMvc.perform(get("/api/v1/orders/1/lines"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetById() throws Exception {

        OrderLineItem mockOrderLineItem = createMockOrderLineItem(3);

        when(mockOrderLineItemService.getById(3, 1))
                .thenReturn(new ResponseEntity<>(mockOrderLineItem, HttpStatus.OK));

        mockMvc.perform(get("/api/v1/orders/1/lines/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testInsert() throws Exception {

        OrderLineItem mockOrderLineItem = createMockOrderLineItem(4);

        when(mockOrderLineItemService.insert(any(OrderLineItem.class), anyLong()))
                .thenReturn(new ResponseEntity<>(mockOrderLineItem, HttpStatus.CREATED));

        mockMvc.perform(post("/api/v1/orders/1/lines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockOrderLineItem)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {

        OrderLineItem mockOrderLineItem = createMockOrderLineItem(1);

        when(mockOrderLineItemService.update(any(OrderLineItem.class), anyLong(), anyLong()))
                .thenReturn(new ResponseEntity<>(mockOrderLineItem, HttpStatus.OK));

        mockMvc.perform(put("/api/v1/orders/1/lines/4")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockOrderLineItem)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {

        when(mockOrderLineItemService.delete(anyLong(), anyLong()))
                .thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        mockMvc.perform(delete("/api/v1/orders/1/lines/5"))
                .andExpect(status().isNoContent());
    }


}