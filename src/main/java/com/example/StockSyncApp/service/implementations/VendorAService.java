package com.example.StockSyncApp.service.implementations;

import com.example.StockSyncApp.domain.Product;
import com.example.StockSyncApp.repository.StockSyncRepository;
import com.example.StockSyncApp.service.ProductService;
import com.example.StockSyncApp.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class VendorAService implements ProductService {

    private StockSyncRepository stockSyncRepository;

    private VendorAService(StockSyncRepository repo){
        this.stockSyncRepository = repo;
    };

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
