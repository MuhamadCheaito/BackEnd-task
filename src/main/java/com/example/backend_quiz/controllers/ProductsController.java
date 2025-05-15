package com.example.backend_quiz.controllers;

import com.example.backend_quiz.services.ProductService;
import com.example.backend_quiz.models.Product;
import com.example.backend_quiz.models.ApiResponse;
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
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        ApiResponse<Product> res = new ApiResponse<>();
        try {
            res.setData(product);
            productService.createProduct(res.getData());
            return ResponseEntity.ok(res);
        }
        catch(Exception ex) {
             res.setError(ex.getMessage());
             ex.printStackTrace();
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
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