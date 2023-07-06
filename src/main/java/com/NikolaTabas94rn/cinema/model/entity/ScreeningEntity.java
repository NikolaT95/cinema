package com.NikolaTabas94rn.cinema.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name="auditorium_id")
    private AuditoriumEntity auditorium;

    private Timestamp screeningStart;

    @OneToMany(mappedBy = "screening")
    @Singular
    private List<ReservationEntity> reservations;

    @OneToMany(mappedBy = "screening")
    @Singular
    private List<SeatReservedEntity> reservedSeats;
}
