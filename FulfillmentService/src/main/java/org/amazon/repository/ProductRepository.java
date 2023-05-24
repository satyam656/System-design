package org.amazon.repository;

import org.amazon.entity.Product;

import java.util.List;

public interface ProductRepository {
    void add(Product product);
    void remove(String productId);
    Product getProductById(String productId);
    List<Product> getAllProducts();

}
