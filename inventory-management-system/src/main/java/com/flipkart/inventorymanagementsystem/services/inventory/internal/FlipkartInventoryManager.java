package com.flipkart.inventorymanagementsystem.services.inventory.internal;

import com.flipkart.inventorymanagementsystem.model.Item;
import com.flipkart.inventorymanagementsystem.utility.ItemProvider;

import java.util.HashMap;
import java.util.Map;

public class FlipkartInventoryManager extends InternalInventoryManager{
    Map<Integer, Item> inventoryIndex;

    public FlipkartInventoryManager() {
        this.inventoryIndex = new HashMap<>();
    }

    @Override
    public void addItemToInventory(int itemId, int quantity) {
        if(ItemProvider.getItemsContext().containsKey(itemId) == false) {
            // throw ItemNotFoundError
            return;
        }

        if(inventoryIndex.containsKey(itemId)) {
            inventoryIndex.get(itemId).incrementAvailableItemCount(quantity);
        }
        else {
            Item item = ItemProvider.getItemsContext().get(itemId);
            item.setAvailableItemCount(quantity);
            inventoryIndex.put(itemId, item);
        }
    }

    @Override
    public int getAvailableInventory(int itemId) {
        if(ItemProvider.getItemsContext().containsKey(itemId) == false)
            return 0;
        return inventoryIndex.get(itemId).getAvailableItemCount();
    }

    @Override
    public void unblockInventoryItems(Map<Integer, Integer> itemIds) {
        for(Map.Entry<Integer, Integer> entry : itemIds.entrySet()) {
            inventoryIndex.get(entry.getKey()).unblockItem(entry.getValue());
        }
    }

    @Override
    public void blockInventoryItems(Map<Integer, Integer> itemIds) {
        for(Map.Entry<Integer, Integer> entry : itemIds.entrySet()) {
            inventoryIndex.get(entry.getKey()).blockItem(entry.getValue());
        }
    }

    @Override
    public void freeInventoryItems(Map<Integer, Integer> itemIds) {
        for(Map.Entry<Integer, Integer> entry : itemIds.entrySet()) {
            inventoryIndex.get(entry.getKey()).freeItem(entry.getValue());
        }
    }
}
