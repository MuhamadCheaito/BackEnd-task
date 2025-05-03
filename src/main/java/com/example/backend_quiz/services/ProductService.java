package com.example.backend_quiz.services;

import com.example.backend_quiz.models.Product;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service 
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1,"Laptop","High-end laptop","Electronics"));
        products.add(new Product(2, "Chair", "Gaming Chair, comfort to your back", "Furniture"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void createProduct(Product product) {
        products.add(product);
    }

    public boolean updateProduct(int id, Product updatedProduct) {
        Optional<Product> optionalProduct = products.stream().filter(p -> p.getId() == id).findFirst();
        if(optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName() != null ? updatedProduct.getName() : existingProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription() != null ? updatedProduct.getDescription() : existingProduct.getDescription());
            existingProduct.setCategory(updatedProduct.getCategory() != null ? updatedProduct.getCategory() : existingProduct.getCategory());
            return true;
        }
        return false;
    }
}