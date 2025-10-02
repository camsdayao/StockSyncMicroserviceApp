package com.example.StockSyncApp.service;

import com.example.StockSyncApp.domain.Product;

import java.util.List;

public interface StockSyncService {
    public List<Product> getProduct();
}