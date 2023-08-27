package com.flipkart.inventorymanagementsystem.services.ordering;

import com.flipkart.inventorymanagementsystem.model.Address;
import com.flipkart.inventorymanagementsystem.model.Item;
import com.flipkart.inventorymanagementsystem.model.Order;
import com.flipkart.inventorymanagementsystem.model.OrderStatus;
import com.flipkart.inventorymanagementsystem.services.inventory.InventoryManager;
import com.flipkart.inventorymanagementsystem.services.inventory.internal.FlipkartInventoryManager;

import java.util.HashMap;
import java.util.Map;

public class FlipkartOrdering implements OrderManager{

    InventoryManager inventoryManager;
    Map<Integer, Order> orderIndex;

    public FlipkartOrdering(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        orderIndex = new HashMap<>();
    }

    @Override
    public Order createOrder(Integer customerId, Map<Item, Integer> itemsInfo, Address address) {

        for(Map.Entry<Item, Integer> entry : itemsInfo.entrySet()) {
            if(entry.getKey().getAvailableItemCount() < entry.getValue())
                return null;
        }

        Order order = new Order(customerId);
        Map<Integer, Integer> blockedItemsList = new HashMap<>();

        for(Map.Entry<Item, Integer> entry : itemsInfo.entrySet()) {
            order.addItem(entry.getKey(), entry.getValue());
            blockedItemsList.put(entry.getKey().getItemId(), entry.getValue());
        }
        inventoryManager.blockInventoryItems(blockedItemsList);
        return order;
    }

    @Override
    public boolean updateOrder(Integer orderId, OrderStatus orderStatus) {
        if(orderIndex.containsKey(orderId) == false) {
            // Throw order not found
            return false;
        }
        Order order = orderIndex.get(orderId);
        if(order.getOrderStatus() == OrderStatus.CANCELLED) {
            return false;
        }

        switch(orderStatus){
            case CANCELLED :
                cancelOrder(order);
                return true;
            case FULFILLED:
                order.setOrderStatus(OrderStatus.FULFILLED);
                freeInventoryForOrder(order);
                return true;
            case CONFIRMED:
                order.setOrderStatus(OrderStatus.CONFIRMED);
                return true;
        }
        return false;
    }

    public void freeInventoryForOrder(Order order) {
        Map<Integer, Integer> blockedItemsList = new HashMap<>();
        Map<Item, Integer> itemsInfo = order.getItems();
        for(Map.Entry<Item, Integer> entry : itemsInfo.entrySet()) {
            blockedItemsList.put(entry.getKey().getItemId(), entry.getValue());
        }
        inventoryManager.freeInventoryItems(blockedItemsList);
    }

    public void cancelOrder(Order order) {
        order.setOrderStatus(OrderStatus.CANCELLED);
        unblockInventoryForOrder(order);
    }

    public void unblockInventoryForOrder(Order order) {
        Map<Item, Integer> itemsInfo = order.getItems();
        Map<Integer, Integer> unblockingItemList = new HashMap<>();
        for(Map.Entry<Item, Integer> entry : itemsInfo.entrySet()) {
            unblockingItemList.put(entry.getKey().getItemId(), entry.getValue());
        }
        inventoryManager.unblockInventoryItems(unblockingItemList);
    }
}
