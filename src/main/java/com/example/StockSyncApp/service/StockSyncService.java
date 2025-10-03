package com.example.StockSyncApp.service;

import com.example.StockSyncApp.domain.Product;
import com.example.StockSyncApp.repository.StockSyncRepository;
import com.example.StockSyncApp.service.implementations.VendorAService;
import com.example.StockSyncApp.service.implementations.VendorBService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockSyncService {

    List<Product> allProduct = new ArrayList<>();
    private final VendorAService vendorAService;
    private final VendorBService vendorBService;
    private final StockSyncRepository stockSyncRepository;



    public void syncStocks(){
        allProduct.addAll(vendorAService.getProduct());
        allProduct.addAll(vendorBService.getProduct());

        for(Product product : allProduct){
            Optional<Product> existing = stockSyncRepository.findBySkuAndVendor(product.getSku(), product.getVendor());
            if(existing.isPresent()){
                Product existingProduct = existing.get();
                if(existingProduct.getStockQuantity() > 0 && product.getStockQuantity() == 0){
                    log.warn("Product {} from {} is now out of stock!", product.getSku(), product.getVendor());
                }
                existingProduct.setStockQuantity(product.getStockQuantity());
                stockSyncRepository.save(existingProduct);
            }
            else{
                stockSyncRepository.save(product);
            }
        }
    }

}
