package com.flipkart.inventorymanagementsystem.services.ordering;

import com.flipkart.inventorymanagementsystem.model.Address;
import com.flipkart.inventorymanagementsystem.model.Item;
import com.flipkart.inventorymanagementsystem.model.Order;
import com.flipkart.inventorymanagementsystem.model.OrderStatus;

import java.util.Map;

public interface OrderManager {
    Order createOrder(Integer customerId, Map<Item, Integer> itemsInfo, Address address);
    boolean updateOrder(Integer orderId, OrderStatus orderStatus);
}
