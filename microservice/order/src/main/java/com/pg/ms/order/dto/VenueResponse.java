package com.pg.ms.order.dto;

public record VenueResponse(
        Long venueId,
        String venueName,
        String address,
        Long totalCapacity
) {
}
