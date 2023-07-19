package com.example.dto;

public class ApiResponseDTO {
    private boolean status;
    private String message;
    private Object data;

    public ApiResponseDTO(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponseDTO(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }
}
