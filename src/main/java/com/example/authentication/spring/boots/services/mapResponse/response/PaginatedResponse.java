package com.example.authentication.spring.boots.services.mapResponse.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "รูปแบบข้อมูลตอบกลับแบบแบ่งหน้า")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse<T> {

    @Schema(description = "รายการข้อมูลในหน้านี้")
    private List<T> items;

    @Schema(description = "ข้อมูลเมตาของการแบ่งหน้า")
    private Meta meta;

    @Schema(description = "ข้อมูลเมตาของหน้าทั้งหมด")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {

        @Schema(description = "จำนวนข้อมูลทั้งหมด", example = "100")
        private long totalItems;

        @Schema(description = "จำนวนข้อมูลในหน้านี้", example = "10")
        private int itemCount;

        @Schema(description = "จำนวนข้อมูลต่อหน้า", example = "10")
        private int itemsPerPage;

        @Schema(description = "จำนวนหน้าทั้งหมด", example = "10")
        private int totalPages;

        @Schema(description = "หน้าปัจจุบัน", example = "1")
        private int currentPage;
    }
}
