package com.example.StockSyncApp.controller;

import com.example.StockSyncApp.domain.Product;
import com.example.StockSyncApp.repository.StockSyncRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StockSyncController {

    private final StockSyncRepository stockSyncRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        log.info("Getting all Products...");
        return stockSyncRepository.findAll();
    }
}
