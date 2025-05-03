package com.example.backend_quiz.models;


import java.util.Date;

public class Product {
    private int id;
    private String name;
    private String description;
    private String category;
    private Date creationDate; 

    public Product() {

    }
    public Product(int id, String name, String description, String category) {
        this.id = id;
        this.name = name; 
        this.description = description;
        this.category = category;
        this.creationDate = new Date();
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
}
