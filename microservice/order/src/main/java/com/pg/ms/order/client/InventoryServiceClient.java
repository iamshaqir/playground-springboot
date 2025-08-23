package com.pg.ms.order.client;

import com.pg.ms.order.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceClient {

    private final RestClient restClient;

    @Value("${inventory.service.url}")
    private String inventoryClientUrl;

    public Integer createVenues() {
        Integer venueCount = restClient.post()
                .uri(inventoryClientUrl + "/create/venues/{size}", 100)
                .retrieve()
                .onStatus(httpStatusCode -> {

                    if (httpStatusCode.getStatusCode().is4xxClientError()) {
                        log.error("Invalid request");
                        throw new RuntimeException("Invalid request");
                    }

                    if (httpStatusCode.getStatusCode().is5xxServerError()) {
                        log.error("Internal server error");
                        throw new RuntimeException("Internal server error");
                    }

                    return false;
                })
                .body(Integer.class);

        log.info("Created Venues in Inventory, [SIZE]:{}", venueCount);
        return venueCount;
    }

    public Integer createEvents() {
        Integer venueCount = restClient.post()
                .uri(inventoryClientUrl + "/create/events")
                .retrieve()
                .onStatus(httpStatusCode -> {

                    if (httpStatusCode.getStatusCode().is4xxClientError()) {
                        log.error("Invalid request");
                        throw new RuntimeException("Invalid request");
                    }

                    if (httpStatusCode.getStatusCode().is5xxServerError()) {
                        log.error("Internal server error");
                        throw new RuntimeException("Internal server error");
                    }

                    return false;
                })
                .body(Integer.class);

        log.info("Created Venues in Inventory, [SIZE]:{}", venueCount);
        return venueCount;
    }

    public InventoryResponse getInventory(final long eventId) {
        ResponseEntity<InventoryResponse> response = restClient.get()
                .uri(inventoryClientUrl + "/event/{eventId}", eventId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(InventoryResponse.class);

        if (response.getStatusCode().is4xxClientError()) {
            log.error("Invalid request");
            throw new RuntimeException("Invalid request");
        }

        if (response.getStatusCode().is5xxServerError()) {
            log.error("Internal server error");
            throw new RuntimeException("Internal server error");
        }

        return response.getBody();

    }

    public List<InventoryResponse> getAllEvents() {
        return restClient.get()
                .uri(inventoryClientUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<InventoryResponse>>() {
                });

    }
}
