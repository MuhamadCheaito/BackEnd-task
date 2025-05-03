package com.example.backend_quiz.models;

import com.example.backend_quiz.models.Transaction;
import com.example.backend_quiz.models.Client;

import java.util.Date;
import java.util.List;

public class Sale {
    private int id;
    private Date creationDate;
    private Client client;
    private String seller;
    private double total;
    private List<Transaction> transactions;

    public Sale() {
        this.creationDate = new Date();
    }

    public Sale(int id, Client client, String seller, double total, List<Transaction> transactions) {
        this.id = id;
        this.client = client;
        this.seller = seller;
        this.total = total;
        this.transactions = transactions;
        this.creationDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

