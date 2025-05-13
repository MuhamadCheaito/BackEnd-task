package com.example.backend_quiz.services;

import com.example.backend_quiz.models.Sale;
import com.example.backend_quiz.models.Transaction;
import com.example.backend_quiz.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    private static final Logger logger = LoggerFactory.getLogger(SaleService.class);

    private List<Sale> sales = new ArrayList<>();

    public SaleService() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, 2, 10.00));
        Sale sale = new Sale(1, new Client(1, "John", "Doe", "123456789"), "Alice", 20.00, transactions);
        sales.add(sale);
    }

    public List<Sale> getAllSales() {
        return sales;
    }

    public Sale createSale(Sale sale) {
    if (sale.getClient() == null || sale.getClient().getId() <= 0) {
        throw new IllegalArgumentException("Invalid client ID.");
    }

    for (Transaction t : sale.getTransactions()) {
        if (t.getProductId() <= 0 || t.getQuantity() <= 0 || t.getUnitPrice() < 0) {
            throw new IllegalArgumentException("Invalid transaction data.");
        }
    }

    sale.setId(sales.size() + 1);
    sales.add(sale);
    logger.info("Created new sale with ID: {}", sale.getId());
    return sale;
}


    public Sale updateSale(int id, Sale updatedSale) {
    if (id <= 0) {
        throw new IllegalArgumentException("Invalid sale ID.");
    }

    Sale sale = getSaleById(id);
    if (sale == null) {
        throw new IllegalArgumentException("Sale with ID " + id + " does not exist.");
    }

    List<Transaction> originalTransactions = sale.getTransactions();
    List<Transaction> updatedTransactions = updatedSale.getTransactions();

    for (int i = 0; i < updatedTransactions.size(); i++) {
        Transaction updatedTransaction = updatedTransactions.get(i);
        if (updatedTransaction.getProductId() <= 0 || updatedTransaction.getQuantity() <= 0 || updatedTransaction.getUnitPrice() < 0) {
            throw new IllegalArgumentException("Invalid transaction data.");
        }

        if (i < originalTransactions.size()) {
            Transaction originalTransaction = originalTransactions.get(i);

            if (originalTransaction.getQuantity() != updatedTransaction.getQuantity()) {
                logger.info("Updated quantity for product {} in sale {}: {} → {}",
                        updatedTransaction.getProductId(), id,
                        originalTransaction.getQuantity(), updatedTransaction.getQuantity());
            }

            if (originalTransaction.getUnitPrice() != updatedTransaction.getUnitPrice()) {
                logger.info("Updated unit price for product {} in sale {}: ${} → ${}",
                        updatedTransaction.getProductId(), id,
                        originalTransaction.getUnitPrice(), updatedTransaction.getUnitPrice());
            }
        }
    }

    sale.setTransactions(updatedTransactions);
    sale.setTotal(updatedSale.getTotal());
    logger.info("Updated sale with ID: {}", id);
    return sale;
}


    public Sale getSaleById(int id) {
    if (id <= 0) {
        throw new IllegalArgumentException("Invalid sale ID.");
    }

    return sales.stream()
            .filter(sale -> sale.getId() == id)
            .findFirst()
            .orElse(null);
}
}