package com.flipkart.inventorymanagementsystem.services.inventory.external;

import java.util.Map;

public class CoolCompanyInventoryManager extends ExternalInventoryManager{
    @Override
    public void addItemToInventory(int itemId, int quantity) {

    }

    @Override
    public int getAvailableInventory(int itemId) {
        return 0;
    }

    @Override
    public void unblockInventoryItems(Map<Integer, Integer> itemIds) {

    }

    @Override
    public void blockInventoryItems(Map<Integer, Integer> itemIds) {

    }

    @Override
    public void freeInventoryItems(Map<Integer, Integer> itemIds) {

    }
}
