package com.example.StockSyncApp.service.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class ProductDto {

    private String sku;
    private String name;
    private Integer stockQuantity;

}
