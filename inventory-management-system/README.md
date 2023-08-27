Design and implement an Order Management System for Flipkart. You have the following aspects to manage via this system.

Inventory: This is the item store available to sell and also has the price per unit. There can be 2 types of inventory:

INTERNAL: This is Flipkart’s internal inventory and is directly managed by the Order Management System. Your system has the items with quantity and price per unit information for items present.
EXTERNAL: This is inventory of external sellers who are registered on Flipkart, this can be used via their apis
Order: When a customer buys something on Flipkart an order is created. It should have basic properties and states that are needed according to the use cases mentioned in the problem.

Mandatory Implementations:

addItemToInventory(itemId, quantity): This is used to add an item or add more quantities of an existing item in Flipkart’s internal inventory. You cannot manage an external seller’s inventory.
getAvailableInventory(itemId, seller): This is used to get available inventory for an item and a seller.
createOrder(customerId, itemsInfo, address etc): This is used to create an order in the system, Customer can purchase multiple items in one order. Creating an order also provisionally reserves the inventory until it is confirmed. All the items in an order will be from the same seller. This api also tells the total amount that the customer needs to pay. This should also make necessary validations and throw errors.
updateOrder(orderId, OrderState): This is used to update an order to confirm, cancel or mark an order fulfilled.
Confirm: order is confirmed after successful payment. The inventory reserved during creation is now BLOCKED.
Cancel*: order is cancelled and any reserved/blocked inventory is unblocked
Fulfilled: order is marked fulfilled once the items are delivered to the customer. Only confirmed orders can be fulfilled
For simplicity we will assume that there is only 1 external seller registered on your platform and it has exposed apis to get/reserve/block inventory

Optional:
Have a mechanism in place to automatically cancel an order and free up the provisional inventory reserved if the order is not confirmed within a particular time duration after creation.

Points to note

Your code should cover all the mandatory functionalities mentioned above.
Your code should be executable and clean.
All necessary validations must be present.
In case of an exception, proper errorCode must be present.
Store the data in-memory for order and the inventory within the system.
Your system should also take care of race conditions.
How will you be evaluated?

Code should be working.
Code readability and testability
Separation Of Concerns
Object-Oriented concepts.
Language proficiency.
SOLID principles
[execution time limit] 3 seconds (java)
[memory limit] 1 GB