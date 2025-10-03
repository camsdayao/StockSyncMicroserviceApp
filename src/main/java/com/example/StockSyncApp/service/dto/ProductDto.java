package com.example.StockSyncApp.service.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String sku;
    private String name;
    private Integer stockQuantity;

}
