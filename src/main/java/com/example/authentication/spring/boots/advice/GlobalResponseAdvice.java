package com.example.authentication.spring.boots.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.authentication.spring.boots.responses.ApiResponse;

@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // apply กับทุก response
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            org.springframework.http.server.ServerHttpRequest request,
            org.springframework.http.server.ServerHttpResponse response) {

        // ป้องกันซ้ำซ้อน ไม่ wrap ซ้อน
        if (body instanceof ApiResponse) {
            return body;
        }

        // ✅ กรณี response เป็น ResponseEntity — แยก status ออกมาอย่างปลอดภัย
        if (body instanceof ResponseEntity<?> entity) {
            Object responseBody = entity.getBody();
            HttpStatusCode statusCode = entity.getStatusCode();
            HttpStatus status = HttpStatus.resolve(statusCode.value());

            return new ApiResponse<>(
                    status != null && status.is2xxSuccessful(),
                    statusCode.value(),
                    (status != null ? status.getReasonPhrase() : "OK"),
                    responseBody);
        }

        // ✅ default ปกติ
        return new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                body);
    }
}
