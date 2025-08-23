package com.pg.ms.inventory.dto;


import com.pg.ms.inventory.model.Venue;

public record VenueDTO(
        Long venueId,
        String venueName,
        String address,

        Integer totalCapacity
) {
    public VenueDTO(Venue venue) {
        this(venue.getId(), venue.getName(), venue.getAddress(), venue.getTotalCapacity());
    }
}
