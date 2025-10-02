package com.example.StockSyncApp.service.implementations;

import com.example.StockSyncApp.domain.Product;
import com.example.StockSyncApp.service.ProductService;
import com.example.StockSyncApp.service.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class VendorAService implements ProductService {

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public List<Product> getProduct() {

        ResponseEntity<ProductDto[]> response = restTemplate.
                getForEntity("http://localhost:8081/vendor-a/products",
                        ProductDto[].class);

        return Arrays.stream(response.getBody())
                .map(dto -> new Product(null, dto.getSku(), dto.getName(), dto.getStockQuantity(), "VendorA"))
                .toList();

    }
}
