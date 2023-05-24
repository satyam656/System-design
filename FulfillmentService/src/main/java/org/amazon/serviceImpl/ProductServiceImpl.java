package org.amazon.serviceImpl;

import org.amazon.entity.Product;
import org.amazon.repository.InMemoryProductRepository;
import org.amazon.repository.ProductRepository;
import org.amazon.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository inMemoryProductRepository;

    public ProductServiceImpl(ProductRepository inMemoryProductRepository) {
        this.inMemoryProductRepository = inMemoryProductRepository;
    }
    @Override
    public void add(Product product) {
        inMemoryProductRepository.add(product);
    }

    @Override
    public void remove(String productId) throws Exception {
        Product product = findProductById(productId);

        int quantity = product.getProductQuantity();

        if(quantity <= 0) {
            throw new Exception("Product with ID " + productId + " is out of stock.");
        }
        quantity--;
        product.setProductQuantity(quantity);
    }

    @Override
    public Product findProductById(String productId) throws Exception {
        Product product = inMemoryProductRepository.getProductById(productId);
        if(product == null)
            throw new Exception("Product with ID " + productId + " not found.");
        return product;
    }

    @Override
    public List<Product> findAllProducts() {
        return inMemoryProductRepository.getAllProducts();
    }
}
