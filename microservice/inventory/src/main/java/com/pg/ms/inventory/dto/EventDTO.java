package com.pg.ms.inventory.dto;


import com.pg.ms.inventory.model.Event;
import com.pg.ms.inventory.model.Venue;

import java.math.BigDecimal;

public record EventDTO(
        Long eventId,
        String event,
        Integer capacity,
        VenueDTO venue,
        BigDecimal ticketPrice
) {
    public EventDTO(Event event) {
        this(event.getId(), event.getName(), event.getTotalCapacity(), event.getVenue().toVenueDTO(), event.getTicketPrice());
    }
}
