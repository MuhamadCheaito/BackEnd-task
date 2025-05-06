package com.example.backend_quiz.controllers;

import com.example.backend_quiz.models.Sale;
import com.example.backend_quiz.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sales")
public class SalesController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable int id) {
        Sale sale = saleService.getSaleById(id);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("new")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale createdSale = saleService.createSale(sale);
        return ResponseEntity.status(201).body(createdSale);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable int id, @RequestBody Sale updatedSale) {
        Sale sale = saleService.updateSale(id, updatedSale);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}