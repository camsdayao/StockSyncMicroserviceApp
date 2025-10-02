package com.example.StockSyncApp.controller;

import com.example.StockSyncApp.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class StockSyncController {

    @GetMapping("/products")
    public List<Product> getAllProducts(){


        log.info("Getting all Products...");

        return List.of(Product.builder()
                        .id(1L)
                        .name("testName")
                        .sku("testSku")
                        .stockQuantity(0)
                        .vendor("testVendor")
                .build());
    }
}
