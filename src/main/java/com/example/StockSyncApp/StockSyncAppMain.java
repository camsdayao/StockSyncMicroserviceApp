package com.example.StockSyncApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StockSyncAppMain {

	public static void main(String[] args) {
		SpringApplication.run(StockSyncAppMain.class, args);
	}

}
