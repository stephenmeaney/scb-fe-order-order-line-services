package com.stephenmeaney.services.orderlineitem.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stephenmeaney.services.order.data.entity.CustomerOrder;
import com.stephenmeaney.services.orderlineitem.client.domain.Product;
import com.stephenmeaney.services.orderlineitem.client.domain.Shipment;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
public class OrderLineItem {

    @Id
    @GeneratedValue
    private long orderLineItemId;

    private int quantity;

    private double price;

    @Formula("quantity * price")
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties(value = {"orderLineItemList"})
    private CustomerOrder customerOrder;

    private long productId;

    @Transient
    private Product product;

    private long shipmentId;

    @Transient
    private Shipment shipment;


    public long getOrderLineItemId() {
        return orderLineItemId;
    }

    public void setOrderLineItemId(long orderLineItemId) {
        this.orderLineItemId = orderLineItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //@JsonIgnore
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    @JsonIgnore
    public void setProduct(Product product) {
        this.product = product;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    @JsonIgnore
    public Shipment getShipment() {
        return shipment;
    }

    @JsonIgnore
    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
