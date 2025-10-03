package com.example.StockSyncApp.scheduler;

import com.example.StockSyncApp.service.StockSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SyncScheduler {

    final private StockSyncService stockSyncService;

    @Scheduled(fixedRate = 60000)
    public void syncStock(){
        stockSyncService.syncStocks();
    }
}
