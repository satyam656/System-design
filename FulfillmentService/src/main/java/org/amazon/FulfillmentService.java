package org.amazon;

import org.amazon.entity.Product;
import org.amazon.repository.InMemoryProductRepository;
import org.amazon.repository.ProductRepository;
import org.amazon.service.ProductService;
import org.amazon.serviceImpl.ProductServiceImpl;

import java.util.List;
import java.util.Scanner;

public class FulfillmentService {
    public static void main(String[] args) throws Exception {
        ProductRepository productRepository = new InMemoryProductRepository();
        ProductService productService = new ProductServiceImpl(productRepository);

        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("1. Add some initial products");
            System.out.println("2. View product");
            System.out.println("3. Remove product");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            if(choice == 1)
            {
                System.out.println("Enter productId: ");
                String productId = sc.next();
                System.out.println("Enter product name: ");
                String productName = sc.next();
                System.out.println("Enter product quantity: ");
                int quantity = sc.nextInt();
                productService.add(new Product(productId, productName, quantity));

            }
            else if(choice == 2)
            {
                System.out.println("Product Inventory List : ");
                List<Product> allProducts = productService.findAllProducts();
                System.out.println(allProducts);
            }
            else if(choice == 3)
            {
                System.out.println("Enter ProductId: ");
                String productId = sc.next();
                productService.remove(productId);
            }
            else if(choice == 4)
            {
                System.exit(0);
            }
            else
            {
                System.out.println("Invalid Choice!");
            }
        }
    }
}