package com.flipkart.inventorymanagementsystem;

import com.flipkart.inventorymanagementsystem.exception.SellerNotFoundException;
import com.flipkart.inventorymanagementsystem.model.*;
import com.flipkart.inventorymanagementsystem.services.inventory.InventoryManager;
import com.flipkart.inventorymanagementsystem.services.inventory.internal.FlipkartInventoryManager;
import com.flipkart.inventorymanagementsystem.services.ordering.FlipkartOrdering;
import com.flipkart.inventorymanagementsystem.services.ordering.OrderManager;
import com.flipkart.inventorymanagementsystem.utility.InventoryProvider;
import com.flipkart.inventorymanagementsystem.utility.ItemProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class InventoryManagementSystemApplication {

	public static void main(String[] args) throws Exception, SellerNotFoundException {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
		InventoryManager inventoryManager = InventoryProvider.getInventory(Seller.FLIPKART);

		inventoryManager.addItemToInventory(0, 10);
		inventoryManager.addItemToInventory(2, 12);
		inventoryManager.addItemToInventory(1, 13);

		System.out.println("Available Inventory for [id = 0]: " + inventoryManager.getAvailableInventory(0));
		System.out.println("Available Inventory for [id = 1]: " + inventoryManager.getAvailableInventory(1));

		OrderManager orderManager = new FlipkartOrdering(inventoryManager);
		int customerID = 1;
		Map<Item, Integer> itemsInfo = new HashMap<>();
		itemsInfo.put(ItemProvider.getItemsContext().get(0), 9);
		Address address = new Address("", 100000, "Bangalore", "India");

		Order order = orderManager.createOrder(customerID, itemsInfo, address);
		System.out.println(order);

		TimeUnit.SECONDS.sleep(4);

		if (orderManager.updateOrder(order.getOrderId(), OrderStatus.CONFIRMED)) {
			System.out.println(order);

			// We can see blocked and freed items in LOGS
			orderManager.updateOrder(order.getOrderId(), OrderStatus.FULFILLED);
			System.out.println(order);
		} else {
			System.out.println("Order already cancelled");
		}

	}

}
