package com.NikolaTabas94rn.cinema.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatReservedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="reservation_id")
    private ReservationEntity reservation;

    @ManyToOne
    @JoinColumn(name="seat_id")
    private SeatEntity seat;

    @ManyToOne
    @JoinColumn(name="screening_id")
    private ScreeningEntity screening;

}
