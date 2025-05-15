package com.example.backend_quiz.models;

public class ApiResponse<T> {
    private T data;
    private String error;

    public ApiResponse() {
    }
    public T getData() {
        return this.data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public String getError() {
        return this.error;
    }
    public void setError(String error) {
        this.error = error;
    }
}