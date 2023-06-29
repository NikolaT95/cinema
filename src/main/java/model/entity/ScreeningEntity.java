package model.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

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
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name="auditorium_id")
    private AuditoriumEntity auditorium;

    private Timestamp screening_start;

    @OneToMany(mappedBy = "screening")
    @Singular
    private List<ReservationEntity> reservations;

    @OneToMany(mappedBy = "screening")
    @Singular
    private List<SeatReservedEntity> reservedSeats;
}
