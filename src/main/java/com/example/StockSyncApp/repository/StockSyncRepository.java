package com.example.StockSyncApp.repository;

import com.example.StockSyncApp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockSyncRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySkuAndVendor(String sku,String vendor);
}
