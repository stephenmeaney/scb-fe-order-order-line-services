package com.stephenmeaney.services.orderlineitem.client;

import com.stephenmeaney.services.orderlineitem.client.domain.Shipment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("shipmentservices")
public interface ShipmentClient {

    @GetMapping("/api/v1/shipments/{id}")
    Shipment getById(@PathVariable(name = "id") long id);
}
