package com.stephenmeaney.services.orderlineitem.data.entity;

import com.stephenmeaney.services.order.data.entity.CustomerOrder;
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
    private CustomerOrder customerOrder;

    private long productId;

    private long shipmentId;

    // getters with endpoints if needed

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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
