package com.example.StockSyncApp.controller;

import com.example.StockSyncApp.domain.Product;
import com.example.StockSyncApp.repository.StockSyncRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class StockSyncControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockSyncRepository stockSyncRepository;

    @BeforeEach
    void setup() {
        stockSyncRepository.deleteAll();
        stockSyncRepository.save(new Product(null, "SKU1", "Prod A", 10, "VendorA"));
    }

    @Test
    void getAllProducts_returnsProducts() throws Exception {
        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sku").value("SKU1"))
                .andExpect(jsonPath("$[0].vendor").value("VendorA"))
                .andExpect(jsonPath("$[0].stockQuantity").value(10));
    }

}