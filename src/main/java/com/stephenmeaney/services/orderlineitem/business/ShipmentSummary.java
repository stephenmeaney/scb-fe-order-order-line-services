package com.stephenmeaney.services.orderlineitem.business;

import java.time.LocalDate;

public class ShipmentSummary {

    private LocalDate shippedDate;

    private LocalDate deliveryDate;

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
