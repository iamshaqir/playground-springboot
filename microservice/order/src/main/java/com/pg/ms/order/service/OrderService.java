package com.pg.ms.order.service;

import com.pg.ms.order.client.InventoryClientOpenFeign;
import com.pg.ms.order.dto.InventoryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final InventoryClientOpenFeign inventoryClientOpenFeign;

    public InventoryResponse getEvents(final Long eventId) {
        InventoryResponse event = inventoryClientOpenFeign.getEvents(eventId);
        log.info("Event and Venue : {}", event);
        return event;
    }
}
