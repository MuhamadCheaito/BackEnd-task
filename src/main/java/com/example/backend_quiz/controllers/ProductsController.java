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
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
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
    public ResponseEntity<String> updatedProduct(@PathVariable int id, @RequestBody Product product) {
        boolean updated = productService.updateProduct(id, product);
        if(updated) {
            return ResponseEntity.ok("Product updated successfully.");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}