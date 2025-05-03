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
        sale.setId(sales.size() + 1);
        sales.add(sale);
        logger.info("Created new sale with ID: {}", sale.getId());
        return sale;
    }

    public Sale updateSale(int id, Sale updatedSale) {
        Sale sale = getSaleById(id);
        if (sale != null) {
            for (int i = 0; i < updatedSale.getTransactions().size(); i++) {
                Transaction originalTransaction = sale.getTransactions().get(i);
                Transaction updatedTransaction = updatedSale.getTransactions().get(i);

                if (originalTransaction.getQuantity() != updatedTransaction.getQuantity()) {
                    logger.info("Updated quantity for product {} in sale {}: {} → {}",
                            updatedTransaction.getProductId(),
                            id,
                            originalTransaction.getQuantity(),
                            updatedTransaction.getQuantity());
                }

                if (originalTransaction.getUnitPrice() != updatedTransaction.getUnitPrice()) {
                    logger.info("Updated unit price for product {} in sale {}: ${} → ${}",
                            updatedTransaction.getProductId(),
                            id,
                            originalTransaction.getUnitPrice(),
                            updatedTransaction.getUnitPrice());
                }
            }

            sale.setTransactions(updatedSale.getTransactions());
            sale.setTotal(updatedSale.getTotal());
            logger.info("Updated sale with ID: {}", id);
            return sale;
        }
        return null;
    }

    public Sale getSaleById(int id) {
        return sales.stream()
                .filter(sale -> sale.getId() == id)
                .findFirst()
                .orElse(null);
    }
}