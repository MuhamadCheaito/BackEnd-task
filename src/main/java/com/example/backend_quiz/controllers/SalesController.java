package com.example.backend_quiz.controllers;

import com.example.backend_quiz.models.Sale;
import com.example.backend_quiz.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sales")
public class SalesController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable int id) {
        try {
            Sale sale = saleService.getSaleById(id);
            if (sale != null) {
                return ResponseEntity.ok(sale);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale not found.");
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("new")
    public ResponseEntity<?> createSale(@RequestBody Sale sale) {
        try {
            Sale createdSale = saleService.createSale(sale);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + ex.getMessage());
        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> updateSale(@PathVariable int id, @RequestBody Sale updatedSale) {
        try {
            Sale sale = saleService.updateSale(id, updatedSale);
            return ResponseEntity.ok(sale);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + ex.getMessage());
        }
    }
}
