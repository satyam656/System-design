package com.flipkart.inventorymanagementsystem.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int orderIdIndex = 0;
    private int orderId = 0;
    private OrderStatus orderStatus;
    private Date date;
    private int customerId;

    private Map<Item, Integer> items;

    public Order(int customerId) {
        this.orderId = orderIdIndex++;
        this.orderStatus = OrderStatus.CREATED;
        this.date = new Date(System.currentTimeMillis());
        this.customerId = customerId;
        this.items = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        this.items.put(item, quantity);
    }

    public static int getOrderIdIndex() {
        return orderIdIndex;
    }

    public static void setOrderIdIndex(int orderIdIndex) {
        Order.orderIdIndex = orderIdIndex;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }
}
