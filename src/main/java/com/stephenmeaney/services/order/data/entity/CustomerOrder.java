package com.stephenmeaney.services.order.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.stephenmeaney.services.order.client.domain.Address;
import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue
    private long orderId;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate orderDate;

    @Transient
    private double totalPrice;

    private long accountId;

    private long shippingAddressId;

    @Transient
    private Address shippingAddress;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"customerOrder"})
    private List<OrderLineItem> orderLineItemList;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @JsonIgnore
    public double getTotalPrice() {
        double calculatedTotalPrice = 0;
        for (OrderLineItem orderLineItem : orderLineItemList) {
            calculatedTotalPrice += orderLineItem.getTotalPrice();
        }

        return calculatedTotalPrice;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    @JsonIgnore
    public Address getShippingAddress() {
        return shippingAddress;
    }

    @JsonIgnore
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderLineItem> getOrderLineItemList() {
        return orderLineItemList;
    }

    public void setOrderLineItemList(List<OrderLineItem> orderLineItemList) {
        this.orderLineItemList = orderLineItemList;
    }
}
