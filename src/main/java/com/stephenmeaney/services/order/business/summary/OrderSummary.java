package com.stephenmeaney.services.order.business.summary;

import com.stephenmeaney.services.order.client.domain.AddressSummary;
import com.stephenmeaney.services.orderlineitem.business.OrderLineItemSummary;

import java.util.List;

public class OrderSummary {

    private long orderId;

    private double totalPrice;

    private long accountId;

    private long addressId;

    private AddressSummary shippingAddress;

    private List<OrderLineItemSummary> lineItems;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public AddressSummary getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressSummary shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderLineItemSummary> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItemSummary> lineItems) {
        this.lineItems = lineItems;
    }
}
