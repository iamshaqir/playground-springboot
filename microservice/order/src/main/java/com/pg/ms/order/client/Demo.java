package com.pg.ms.order.client;

import com.pg.ms.order.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Demo implements CommandLineRunner {

    private final InventoryServiceClient inventoryServiceClient;

    @Override
    public void run(String... args) throws Exception {
    }

    private void inventorDemo() {
        log.info("--- Creating Venues ---");
        Integer venueCount = inventoryServiceClient.createVenues();
        log.info("--- created {} Venues ---", venueCount);

        log.info("--- Creating Events ---");
        Integer eventCount = inventoryServiceClient.createEvents();
        log.info("--- created {} Events ---", eventCount);

        log.info("--- Getting Inventory for Event 1 ---");
        InventoryResponse inventory = inventoryServiceClient.getInventory(1L);
        log.info("Inventory response:{}", inventory);

        log.info("--- Getting all Inventory ---");
        List<InventoryResponse> inventoryList = inventoryServiceClient.getAllEvents();
        inventoryList.stream()
                .filter(inv -> inv.venue().venueId() != null)
                .forEach(this::logElement);
    }

    public void logElement(Object element) {
        log.info("Inventory: {}", element);
    }
}
