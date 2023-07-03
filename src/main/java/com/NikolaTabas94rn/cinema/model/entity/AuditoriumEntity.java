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
public class AuditoriumEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int seats_no;
    @OneToMany(mappedBy = "auditorium")
    @Singular
    private List<ScreeningEntity> screenings;
    @OneToMany(mappedBy = "auditorium")
    @Singular
    private List<SeatEntity> seats;

}
