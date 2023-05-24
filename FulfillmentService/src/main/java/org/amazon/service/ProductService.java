package org.amazon.service;
import org.amazon.entity.Product;
import java.util.List;

public interface ProductService {
    public void add(Product product);
    public void remove(String productId) throws Exception;
    public Product findProductById(String productId) throws Exception;
    public List<Product> findAllProducts();
}
