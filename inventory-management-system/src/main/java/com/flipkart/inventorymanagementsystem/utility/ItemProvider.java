package com.flipkart.inventorymanagementsystem.utility;

import com.flipkart.inventorymanagementsystem.model.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemProvider {
    public static final Map<Integer, Item> items = new HashMap<>();

    public static Map<Integer, Item> getItemsContext() {
        if(items.isEmpty()) {
            mockItemData();
        }
        return items;
    }

    public static void mockItemData() {
        Item item;
        item = new Item("Camera", 100);
        items.put(item.getItemId(), item);

        item = new Item("Bottle", 20);
        items.put(item.getItemId(), item);

        item = new Item("Book", 10);
        items.put(item.getItemId(), item);

        item = new Item("Drone", 250);
        items.put(item.getItemId(), item);
    }
}
