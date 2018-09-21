package com.stephenmeaney.services.order.business.projection;

import com.stephenmeaney.services.order.data.entity.CustomerOrder;
import com.stephenmeaney.services.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.Projection;

import java.sql.Timestamp;
import java.util.List;

@Projection(name = "OrderSummaryProjection", types = CustomerOrder.class)
public interface OrderSummaryProjection {

    String getOrderId();
    AddressSummary getShippingAddress();
    double getTotalPrice();
    List<OrderLineItemSummary> getOrderLineItemList();

    interface AddressSummary {

        String getStreet();
        String getAptBuilding();
        String getCity();
        String getStateProvince();
        String getZipPostalCode();
        String getCountry();
    }

    interface OrderLineItemSummary {
//        ProductSummary getProduct();
//        interface ProductSummary {
//            String getName();
//        }
//        String getProductName();
        int getQuantity();
    }
}
