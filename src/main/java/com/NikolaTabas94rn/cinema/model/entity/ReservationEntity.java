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
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="screening_id")
    private ScreeningEntity screening;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userReserved;
    @Column(nullable = false)
    private Boolean paid;
    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "reservation")
    @Singular
    private List<SeatReservedEntity> reservedSeats;



}
