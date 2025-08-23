package com.pg.ms.inventory.controller;

import com.pg.ms.inventory.dto.EventDTO;
import com.pg.ms.inventory.dto.VenueDTO;
import com.pg.ms.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/venue/{venueId}")
    @ResponseStatus(HttpStatus.OK)
    public VenueDTO findByVenueId(@PathVariable("venueId") Long id) {
        return inventoryService.findByVenueId(id);
    }

    @GetMapping("/event/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDTO findByEventId(@PathVariable("eventId") Long id) {
        return inventoryService.findByEventId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventDTO> findAllEvents() {
        return inventoryService.findAllEvents();
    }

    @PostMapping("/create/venues/{size}")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer saveVenue(@PathVariable("size") Integer size) {
        log.info("Registering [{}] Venues", size);
        return inventoryService.saveVenues(size);
    }

    @PostMapping("create/events")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer saveEvent() {
        return inventoryService.saveEvents();
    }

    @PutMapping("/event/{eventId}/capacity/{capacity}")
    public void updateEventCapacity(
            @PathVariable("eventId") Long eventId,
            @PathVariable("capacity") Integer ticketsBooked
    ) {
        inventoryService.updateCapacity(eventId, ticketsBooked);
    }
}
