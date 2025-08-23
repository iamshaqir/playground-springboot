package com.pg.ms.inventory.model;

import com.pg.ms.inventory.dto.EventDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_capacity")
    private Integer totalCapacity;

    @Column(name = "left_capacity")
    private Integer leftCapacity;

    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    public EventDTO toEventDTO() {
        return new EventDTO(this);
    }
}
