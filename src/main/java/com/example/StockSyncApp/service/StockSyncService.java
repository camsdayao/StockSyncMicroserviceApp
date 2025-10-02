package com.example.StockSyncApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StockSyncService {

    @Scheduled(fixedRate = 10000)
    void sync(){
        log.info("CAMS1");
    }

}
