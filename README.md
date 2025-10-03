# StockSyncMicroserviceApp
StockSync is a Spring Boot microservice that periodically synchronizes product stock data from multiple vendors, normalizes it, and exposes the latest inventory through a REST API.

## Build Requisites
- Maven: apache-maven-3.9.6
- Java: 17.0.10
- Docker:  24.0.7

## Setup Instruction
1. make sure your docker desktop is running 
2. on your terminal, execute `mvn clean install`
3. wait for the build to finish
4. on your terminal, go to `cd src\test\resources\mockVendorA`
5. execute `docker compose up`
6. wait for the wiremock of VendorA to be up and running
7. run the main application `StockSyncAppMain`

## Simulation of VendorA
1. Vendor A is using WireMock running in a Docker container.

## Simulation of VendorB
1. The file is placed under /tmp/vendor-b/stock.csv.
2. The application reads the file, parses each line, and converts it into product objects.

## Assumption
1. Vendor A’s API is always reachable and returns valid JSON (simulated using WireMock in Docker).
2. Vendor B always provides a valid CSV file in /tmp/vendor-b/stock.csv.
3. Stock sync runs every 60 seconds to catch stock changes.

## Trade-Off
1. Used Wiremock for VendorA reliable local simulation.
2. Implemented using log.warn to notify when stock transitions to zero.
3. Stock synchronization is triggered by Spring’s @Scheduled annotation for simpler demonstration.



