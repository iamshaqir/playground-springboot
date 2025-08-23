package com.pg.ms.inventory.model;

import com.pg.ms.inventory.dto.VenueDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venue")
public class Venue {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "total_capacity")
    private Integer totalCapacity;

    public VenueDTO toVenueDTO() {
        return new VenueDTO(this);
    }
}
