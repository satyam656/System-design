package com.flipkart.inventorymanagementsystem.utility;

import com.flipkart.inventorymanagementsystem.exception.SellerNotFoundException;
import com.flipkart.inventorymanagementsystem.model.Seller;
import com.flipkart.inventorymanagementsystem.services.inventory.InventoryManager;
import com.flipkart.inventorymanagementsystem.services.inventory.external.CoolCompanyInventoryManager;
import com.flipkart.inventorymanagementsystem.services.inventory.internal.FlipkartInventoryManager;

public class InventoryProvider {
    public static InventoryManager getInventory(Seller seller) throws SellerNotFoundException
    {
        switch (seller) {
            case FLIPKART :
                return new FlipkartInventoryManager();
            case EXTERNAL_COOL_COMPANY:
                return new CoolCompanyInventoryManager();
        }
        throw new SellerNotFoundException();
    }
}
