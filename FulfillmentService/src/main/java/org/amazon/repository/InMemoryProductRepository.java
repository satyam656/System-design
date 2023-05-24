package org.amazon.repository;

import java.util.*;

import org.amazon.entity.Product;

public class InMemoryProductRepository implements ProductRepository{
    Map<String, Product> products;

    public InMemoryProductRepository() {
        products = new HashMap<String, Product>();
    }
    @Override
    public void add(Product product) {
        products.put(product.getProductId(), product);
    }

    @Override
    public void remove(String productId) {
        products.remove(productId);
    }

    @Override
    public Product getProductById(String productId) {
        return products.get(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        Collection<Product> allProducts = products.values();
        return new ArrayList<>(allProducts);
    }


}
