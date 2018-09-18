package com.stephenmeaney.services.order.data.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private long orderId;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime orderDate;

    // calculate within getter
    //@Transient
    //private double totalPrice;

    private long accountId;

    private long addressId;

    // ??? add getters with @JsonIgnore that hit feign client endpoints
    // using this class's 'foreign key' ids so that the projection can be populated ???
    // eg. get Account by id, get orderLineItems by orderNumber


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
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
}
