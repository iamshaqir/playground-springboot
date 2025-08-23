package com.pg.ms.order.dto;

import java.math.BigDecimal;

public record InventoryResponse(
        Long eventId,
        String event,
        Integer capacity,
        VenueResponse venue,
        BigDecimal ticketPrice
) {
}
