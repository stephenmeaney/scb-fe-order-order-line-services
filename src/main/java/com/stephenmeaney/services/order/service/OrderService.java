package com.stephenmeaney.services.order.service;

import com.stephenmeaney.services.order.business.projection.OrderSummaryProjection;
import com.stephenmeaney.services.order.business.summary.OrderSummary;
import com.stephenmeaney.services.order.client.AddressClient;
import com.stephenmeaney.services.order.client.domain.Address;
import com.stephenmeaney.services.order.client.domain.AddressSummary;
import com.stephenmeaney.services.order.data.entity.CustomerOrder;
import com.stephenmeaney.services.order.data.repository.OrderRepository;
import com.stephenmeaney.services.orderlineitem.business.OrderLineItemSummary;
import com.stephenmeaney.services.orderlineitem.business.ShipmentSummary;
import com.stephenmeaney.services.orderlineitem.client.ProductClient;
import com.stephenmeaney.services.orderlineitem.client.ShipmentClient;
import com.stephenmeaney.services.orderlineitem.client.domain.Shipment;
import com.stephenmeaney.services.orderlineitem.data.entity.OrderLineItem;
import com.stephenmeaney.services.orderlineitem.service.OrderLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderLineItemService orderLineItemService;
    private AddressClient addressClient;
    private ProductClient productClient;
    private ShipmentClient shipmentClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderLineItemService orderLineItemService, AddressClient addressClient, ProductClient productClient, ShipmentClient shipmentClient) {
        this.orderRepository = orderRepository;
        this.orderLineItemService = orderLineItemService;
        this.addressClient = addressClient;
        this.productClient = productClient;
        this.shipmentClient = shipmentClient;
    }

    //TODO
    // service should return response entities

    public CustomerOrder getById(long id) {
        return orderRepository.findById(id);
    }

    public List<CustomerOrder> getAll() {
        return orderRepository.findAll();
    }

    public List<CustomerOrder> getAllForAccount(long accountId) {
        return orderRepository.findAllByAccountIdOrderByOrderDate(accountId);
    }

    public CustomerOrder insert(CustomerOrder customerOrder) {
        //return orderRepository.save(customerOrder);

        Address address = addressClient.getById(customerOrder.getShippingAddressId(), customerOrder.getAccountId()).getBody();
        customerOrder.setShippingAddress(address);
        if (address == null) {
            System.out.println("\n\nAddress is null\n\n");
        }
        return orderRepository.save(customerOrder);
    }

    public CustomerOrder update(long id, CustomerOrder newCustomerOrder) {
        // would normally check contract for "id not found" behavior and how to handle incomplete entity
        newCustomerOrder.setOrderId(id);
        return orderRepository.save(newCustomerOrder);
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
    }

//    public List<OrderSummaryProjection> getOrderSummary(long accountId) {
//        return orderRepository.findAllProjectedByAccountId(accountId);
//    }

    public List<OrderSummary> getOrderSummary(long accountId) {
        List<CustomerOrder> orderList = orderRepository.findAllByAccountId(accountId);
        List<OrderSummary> orderSummaryList = new ArrayList<>();

        for (CustomerOrder order : orderList) {
            OrderSummary orderSummary = new OrderSummary();
            orderSummary.setOrderId(order.getOrderId());
            orderSummary.setTotalPrice(getTotalPrice(order.getOrderId()));
            orderSummary.setShippingAddress(getAddressSummary(order.getShippingAddressId(), accountId));
            orderSummary.setLineItems(getOrderLineItemSummary(order.getOrderId()));

            orderSummaryList.add(orderSummary);
        }
        return orderSummaryList;
    }

    private AddressSummary getAddressSummary(long addressId, long accountId) {
        Address address = addressClient.getById(addressId, accountId).getBody();
        AddressSummary addressSummary = new AddressSummary();

        addressSummary.setCity(address.getCity());
        addressSummary.setCountry(address.getCountry());

        return addressSummary;
    }

    private List<OrderLineItemSummary> getOrderLineItemSummary(long orderId) {
        List<OrderLineItem> orderLineItemList = orderLineItemService.getAll(orderId).getBody();
        List<OrderLineItemSummary> orderLineItemSummaryList = new ArrayList<>();

        for (OrderLineItem orderLineItem : orderLineItemList) {
            OrderLineItemSummary orderLineItemSummary = new OrderLineItemSummary();
            orderLineItemSummary.setQuantity(orderLineItem.getQuantity());
            orderLineItemSummary.setProductName(productClient.getById(orderLineItem.getProductId()).getName());
            orderLineItemSummary.setShipment(getShipmentSummary(orderLineItem.getShipmentId()));

            orderLineItemSummaryList.add(orderLineItemSummary);
        }
        return orderLineItemSummaryList;
    }

    private ShipmentSummary getShipmentSummary(long shipmentId) {
        Shipment shipment = shipmentClient.getById(shipmentId);
        ShipmentSummary shipmentSummary = new ShipmentSummary();

        shipmentSummary.setShippedDate(shipment.getShippedDate());
        shipmentSummary.setDeliveryDate(shipment.getDeliveryDate());

        return shipmentSummary;
    }

    private double getTotalPrice(long orderId) {
        List<OrderLineItem> orderLineItemList = orderLineItemService.getAll(orderId).getBody();
        double totalPrice = 0;

        for (OrderLineItem orderLineItem : orderLineItemList) {
            totalPrice += orderLineItem.getPrice() * orderLineItem.getQuantity();
        }
        return totalPrice;
    }

}
