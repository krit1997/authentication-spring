package com.example.authentication.spring.boots.services.mapResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.authentication.spring.boots.services.mapResponse.response.PaginatedResponse;

@Service
public class ToPaginatedResponse {

    public <T> PaginatedResponse<T> execute(Page<T> page) {
        return new PaginatedResponse<>(
                page.getContent(),
                new PaginatedResponse.Meta(
                        page.getTotalElements(),
                        page.getNumberOfElements(),
                        page.getSize(),
                        page.getTotalPages(),
                        page.getNumber() + 1));
    }
}
