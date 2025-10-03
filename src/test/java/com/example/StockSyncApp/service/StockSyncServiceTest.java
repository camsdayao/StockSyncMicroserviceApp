package com.example.StockSyncApp.service;

import com.example.StockSyncApp.domain.Product;
import com.example.StockSyncApp.repository.StockSyncRepository;
import com.example.StockSyncApp.service.implementations.VendorAService;
import com.example.StockSyncApp.service.implementations.VendorBService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class StockSyncServiceTest {
    @Mock
    private VendorAService mockVendorA;

    @Mock
    private VendorBService mockVendorB;

    @Mock
    private StockSyncRepository mockStockSyncRepository;

    @InjectMocks
    private StockSyncService stockSyncService;

    @Test
    void shouldSaveNewProducts() {
        Product product = new Product(null, "SKU1", "Prod 1", 5, "VendorA");

        when(mockVendorA.getProduct()).thenReturn(List.of(product));
        when(mockVendorB.getProduct()).thenReturn(List.of());
        when(mockStockSyncRepository.findBySkuAndVendor(anyString(), anyString()))
                .thenReturn(Optional.empty());

        stockSyncService.syncStocks();

        verify(mockStockSyncRepository).save(product);
    }

    @Test
    void shouldLogWhenProductGoesOutOfStock(CapturedOutput output) {
        Product existing = new Product(1L, "SKU2", "Prod 2", 3, "VendorB");
        Product updated = new Product(null, "SKU2", "Prod 2", 0, "VendorB");

        when(mockVendorA.getProduct()).thenReturn(List.of());
        when(mockVendorB.getProduct()).thenReturn(List.of(updated));
        when(mockStockSyncRepository.findBySkuAndVendor("SKU2", "VendorB"))
                .thenReturn(Optional.of(existing));

        stockSyncService.syncStocks();

        verify(mockStockSyncRepository).save(existing);
        assertThat(output.getOut()).contains("Product SKU2 from VendorB is now out of stock!");
    }
}