package com.pg.ms.order.controller;

import com.pg.ms.order.dto.InventoryResponse;
import com.pg.ms.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/event/{eventId}")
    public InventoryResponse findEvent(@PathVariable("eventId") Long eventId) {
        log.info("Fetching inventory for [EVENT ID]: {}", eventId);
        return orderService.getEvents(eventId);
    }
}
