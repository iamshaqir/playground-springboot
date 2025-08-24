package com.pg.ms.order.client;

import com.pg.ms.order.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventoryService", url = "${inventory.service.url}")
public interface InventoryClientOpenFeign {
    @GetMapping("/event/{eventId}")
    InventoryResponse getEvents(@PathVariable("eventId") Long eventId);
}
