package com.NikolaTabas94rn.cinema.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int row;

    private int number;

    @ManyToOne
    @JoinColumn(name="auditorium_id")
    private AuditoriumEntity auditorium;

    @OneToMany(mappedBy = "seat")
    @Singular
    private List<SeatReservedEntity> reservedSeats;
}
