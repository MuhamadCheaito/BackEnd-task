package com.example.backend_quiz.controllers;

import com.example.backend_quiz.services.ProductService;
import com.example.backend_quiz.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/products")
public class ProductsController {
    
    @Autowired
    private ProductService productService;

    @GetMapping 
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @PostMapping("new")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            productService.createProduct(product);
            return ResponseEntity.ok(product);
        }
        catch(Exception ex) {
             ex.printStackTrace();
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("edit/{id}") 
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            boolean updated = productService.updateProduct(id, product);
            if (updated) {
                Product updatedProduct = productService.getProductById(id);
                return ResponseEntity.ok(updatedProduct); // Return only the updated product
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
        }
    }
}