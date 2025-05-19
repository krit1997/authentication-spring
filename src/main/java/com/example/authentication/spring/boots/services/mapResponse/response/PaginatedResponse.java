package com.example.authentication.spring.boots.services.mapResponse.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse<T> {
    private List<T> items;
    private Meta meta;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {
        private long totalItems;
        private int itemCount;
        private int itemsPerPage;
        private int totalPages;
        private int currentPage;
    }
}
