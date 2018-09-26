package com.stephenmeaney.services.orderlineitem.client;

import com.stephenmeaney.services.orderlineitem.client.domain.Shipment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ShipmentClientFallbackImpl implements ShipmentClient {
    @Override
    public Shipment getById(long id) {
        Shipment fallbackShipment = new Shipment();
        fallbackShipment.setShippedDate(LocalDate.parse("0000-01-01"));
        fallbackShipment.setDeliveryDate(LocalDate.parse("0000-01-01"));
        return fallbackShipment;
    }
}
