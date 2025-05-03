package com.example.backend_quiz.models;


public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String mobile;

    public Client(){

    }
    public Client(int id, String firstName, String lastName, String mobile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}