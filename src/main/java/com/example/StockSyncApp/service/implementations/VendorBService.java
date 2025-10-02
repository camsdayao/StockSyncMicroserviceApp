package com.example.StockSyncApp.service.implementations;

import com.example.StockSyncApp.domain.Product;
import com.example.StockSyncApp.service.StockSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VendorBService implements StockSyncService {

    @Value("${vendorB.file-path}")
    private String filePath;

    @Override
    public List<Product> getProduct() {
        List<Product> products = new ArrayList<>();


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            log.info(String.valueOf("CAMS " + bufferedReader));
            boolean isFirstLine = true;
            String line;

            while((line = bufferedReader.readLine())!= null){
                if(isFirstLine){
                    isFirstLine = false;
                }
                else{
                    String columns [] = line.split(",");
                    log.info(columns[0]);

                    Product extractedProduct = Product.builder()
                            .sku(columns[0])
                            .name(columns[1])
                            .stockQuantity(Integer.valueOf(columns[2]))
                            .vendor("VendorB")
                            .build();

                    products.add(extractedProduct);

                }
                log.info("Line: " + line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
