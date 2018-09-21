package com.stephenmeaney.services.orderlineitem.business;

public class OrderLineItemSummary {

    private String productName;

    private int quantity;

    ShipmentSummary shipment;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShipmentSummary getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentSummary shipment) {
        this.shipment = shipment;
    }
}
