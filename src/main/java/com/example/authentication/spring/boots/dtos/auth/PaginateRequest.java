package com.example.authentication.spring.boots.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginateRequest {
    @Schema(description = "หน้าที่ต้องการดึงข้อมูล", example = "1")
    private int page;

    @Schema(description = "จำนวนรายการที่ต้องการในแต่ละหน้า", example = "10")
    private int limit;

    @Schema(description = "ชื่อคอลัมน์ที่ใช้ในการค้นหา", example = "first_name", allowableValues = {
            "first_name", "email", "role" })
    private String searchByColumn;

    @Schema(description = "คำค้นหาหรือคีย์เวิร์ด", example = "krit.t")
    private String search;

    @Schema(description = "ชื่อคอลัมน์ที่ใช้ในการเรียงลำดับ", example = "updated_time", allowableValues = {
            "first_name", "email", "created_time", "updated_time" })
    private String sortByColumn;

    @Schema(description = "ลำดับการเรียง (ASC หรือ DESC)", example = "desc", allowableValues = { "asc",
            "desc" })
    private String order;
}
