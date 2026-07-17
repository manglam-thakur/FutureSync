package com.kumarmanglam.futuresync.dto;

public class MotivationResponse {

    private String message;
    private String category;

    public MotivationResponse() {
    }

    public MotivationResponse(String message, String category) {
        this.message = message;
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public String getCategory() {
        return category;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
