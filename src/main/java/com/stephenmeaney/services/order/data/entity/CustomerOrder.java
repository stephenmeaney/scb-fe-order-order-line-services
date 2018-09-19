package com.stephenmeaney.services.order.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deliveryDate;

    @Transient
    private double totalPrice;

    private long accountId;

    private long addressId;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItemList;

    // ??? add getters with @JsonIgnore that hit feign client endpoints
    // using this class's 'foreign key' ids so that the projection can be populated ???
    // eg. get Account by id, get orderLineItems by orderNumber


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

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public List<OrderLineItem> getOrderLineItemList() {
        return orderLineItemList;
    }

    public void setOrderLineItemList(List<OrderLineItem> orderLineItemList) {
        this.orderLineItemList = orderLineItemList;
    }
}
