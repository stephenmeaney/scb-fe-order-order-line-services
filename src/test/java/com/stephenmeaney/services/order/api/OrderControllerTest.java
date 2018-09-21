package com.stephenmeaney.services.order.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stephenmeaney.services.order.data.entity.CustomerOrder;
import com.stephenmeaney.services.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class, secure = false)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper mapper;

    private CustomerOrder createMockOrder(long num) {
        CustomerOrder mockOrder = new CustomerOrder();

        mockOrder.setOrderId(num);
        mockOrder.setOrderDate(LocalDate.parse("2018-03-03"));
        mockOrder.setOrderId(num);
        mockOrder.setShippingAddressId(num);

        return mockOrder;
    }

    @Test
    public void testGetAll() throws Exception {

        List<CustomerOrder> mockOrderList = new ArrayList<>();
        mockOrderList.add(createMockOrder(1L));

        when(orderService.getAll()).thenReturn(mockOrderList);

        mockMvc.perform(get("/api/v1/orders"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetById() throws Exception {

        CustomerOrder mockOrder = createMockOrder(2L);

        when(orderService.getById(2)).thenReturn(mockOrder);

        mockMvc.perform(get("/api/v1/orders/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testInsert() throws Exception {

        CustomerOrder mockOrder = createMockOrder(4);

        when(orderService.insert(any(CustomerOrder.class))).thenReturn(mockOrder);

        mockMvc.perform(post("/api/v1/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockOrder)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {

        CustomerOrder mockOrder = createMockOrder(1);

        when(orderService.update(anyLong(), any(CustomerOrder.class))).thenReturn(mockOrder);

        mockMvc.perform(put("/api/v1/orders/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockOrder)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/api/v1/orders/3"))
                .andExpect(status().isNoContent());
    }

}