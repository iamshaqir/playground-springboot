package com.pg.ms.inventory.service;

import com.github.javafaker.Faker;
import com.pg.ms.inventory.dto.EventDTO;
import com.pg.ms.inventory.dto.VenueDTO;
import com.pg.ms.inventory.model.Event;
import com.pg.ms.inventory.model.Venue;
import com.pg.ms.inventory.repository.EventRepository;
import com.pg.ms.inventory.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final Faker faker;
    private final VenueRepository venueRepository;
    private final EventRepository eventRepository;

    public VenueDTO findByVenueId(final Long venueId) {

        Venue venue = venueRepository.findById(venueId).orElseThrow(() -> {
            log.error("No element found with [Venue id]: {}", venueId);
            return new NoSuchElementException("No element with Venue id" + venueId);
        });
        return venue.toVenueDTO();
    }

    public EventDTO findByEventId(final Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> {
            log.error("No element found with [Event id]: {}", eventId);
            return new NoSuchElementException("No element with Event id" + eventId);
        });
        return event.toEventDTO();
    }

    public List<EventDTO> findAllEvents() {
        final List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            log.error("No [EVENTS] found");
            throw new NoSuchElementException("Events not found");
        }

        return events.stream()
                .map(Event::toEventDTO)
                .toList();
    }

    public void updateCapacity(final Long eventId, final Integer ticketsBooked) {

        Event event = eventRepository.findById(eventId).orElseThrow(() -> {
            log.error("No element found with [Event id]: {}", eventId);
            return new NoSuchElementException("No element with Event id" + eventId);
        });

        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepository.saveAndFlush(event);
        log.info("Updated event capacity for event id: {} with tickets booked: {}", eventId, ticketsBooked);
    }

    public Integer saveVenues(final int size) {
        List<Venue> venues = IntStream.range(0, size)
                .mapToObj(i -> Venue.builder()
                        .name(faker.company().name())
                        .address(faker.address().fullAddress())
                        .totalCapacity(faker.number().numberBetween(999, 9999))
                        .build()
                )
                .toList();
        return venueRepository.saveAll(venues).size();
    }

    public Integer saveEvents() {
        List<Venue> venues = venueRepository.findAll();
        if (venues.isEmpty()) {
            log.error("No [VENUES] found to add [EVENTS]");
            throw new NoSuchElementException("Venues not found");
        }
        venues.forEach(venue -> {
            int noOfEvents = RandomGenerator.getDefault().nextInt(venues.size() / 20) + 1;
            List<Event> events = createEvent(noOfEvents, venue);
            eventRepository.saveAll(events);
        });
        long events = eventRepository.count();
        log.info("Registered [{}] events", events);
        return (int) events;
    }

    private List<Event> createEvent(int noOfEvents, Venue venue) {
        return IntStream.range(0, noOfEvents)
                .mapToObj(i -> {
                    Event event = Event.builder()
                            .name(faker.name().name())
                            .totalCapacity(faker.number().numberBetween(99, 999))
                            .ticketPrice(new BigDecimal(faker.commerce().price()))
                            .build();
                    event.setVenue(venue);
                    return event;
                }).toList();
    }
}
