package com.example.authentication.spring.boots.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Generic API response wrapper")
public class ApiResponse<T> {

    @Schema(description = "ผลลัพธ์ของ API ว่าสำเร็จหรือไม่", example = "true")
    private boolean result;

    @Schema(description = "HTTP Status code", example = "200")
    private int statusCode;

    @Schema(description = "ข้อความแสดงผล เช่น OK หรือ Error", example = "OK")
    private String message;

    @Schema(description = "ข้อมูล payload ตามประเภทของ API", nullable = true)
    private T data;

    public ApiResponse(boolean result, int statusCode, String message, T data) {
        this.result = result;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
