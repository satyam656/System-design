package com.flipkart.inventorymanagementsystem.model;

public class Item {
    // preferably a database auto increment key
    private static int itemIdIndex = 0;
    private Integer itemId;
    private String name;
    private int availableItemCount;
    private double price;
    private int blockedItemCount;

    public Item(String name, int availableItemCount, double price) {
        this.itemId = itemIdIndex++;
        this.name = name;
        this.availableItemCount = availableItemCount;
        this.price = price;
        this.blockedItemCount = 0;
    }

    public Item(String name, double price) {
        this(name, 0, price);
    }

    public static int getItemIdIndex() {
        return itemIdIndex;
    }

    public int getItemId() {
        return this.itemId;
    }

    public String getName() {
        return this.name;
    }

    public static void setItemIdIndex(int itemIdIndex) {
        Item.itemIdIndex = itemIdIndex;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableItemCount() {
        return availableItemCount;
    }

    public void setAvailableItemCount(int availableItemCount) {
        this.availableItemCount = availableItemCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBlockedItemCount() {
        return blockedItemCount;
    }

    public void setBlockedItemCount(int blockedItemCount) {
        this.blockedItemCount = blockedItemCount;
    }

    public void incrementAvailableItemCount(int additionalCount) {
        this.availableItemCount += additionalCount;
    }

    public void blockItem(int itemCount) {
        if(itemCount > this.availableItemCount) {
            // throw items not in stock error
            return;
        }
        this.blockedItemCount += itemCount;
        this.availableItemCount -= itemCount;
    }

    public void unblockItem(int itemCount) {
        this.blockedItemCount -= itemCount;
        this.blockedItemCount = Math.max(0, this.blockedItemCount);
        this.availableItemCount += itemCount;
    }

    public void freeItem(int itemCount) {
        this.blockedItemCount -= itemCount;
        this.blockedItemCount = Math.max(0, this.blockedItemCount);
    }
}
