package com.NikolaTabas94rn.cinema.stub;

import com.NikolaTabas94rn.cinema.model.entity.*;
import com.NikolaTabas94rn.cinema.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final AuditoriumsRepository auditoriumsRepository;
    private final MoviesRepository moviesRepository;
    private final ReservationsRepository reservationsRepository;
    private final ScreeningsRepository screeningsRepository;
    private final SeatsRepository seatsRepository;
    private final SeatsReservedRepository seatsReservedRepository;
    @Override
    public void run(String... args) throws Exception {
        if(!Arrays.asList(args).contains("--init-db")) {
            return;
        }
        UserEntity user1 = UserEntity.builder()
                .email("pera.peric@example.com")
                .password("pera")
                .build();
        UserEntity user2 = UserEntity.builder()
                .email("milica@example.com")
                .password("milica")
                .build();

        usersRepository.saveAll(Arrays.asList(user1, user2));

        MovieEntity movie1=MovieEntity.builder()
                .title("film1")
                .director("joca")
                .genre("drama")
                .duration_min(120)
                .build();

        MovieEntity movie2=MovieEntity.builder()
                .title("film2")
                .director("zika")
                .genre("komedija")
                .duration_min(90)
                .build();

        moviesRepository.saveAll(Arrays.asList(movie1,movie2));

        AuditoriumEntity auditorium1=AuditoriumEntity.builder()
                .name("mala sala")
                .seats_no(10)
                .build();

        AuditoriumEntity auditorium2= AuditoriumEntity.builder()
                .name("velika sala")
                .seats_no(20)
                .build();

        auditoriumsRepository.saveAll(Arrays.asList(auditorium1,auditorium2));

        ScreeningEntity screening1=ScreeningEntity.builder()
                .movie(movie1)
                .auditorium(auditorium1)
                .screening_start(Timestamp.valueOf("2023-06-29 12:34:56"))
                .build();

        ScreeningEntity screening2=ScreeningEntity.builder()
                .movie(movie2)
                .auditorium(auditorium1)
                .screening_start(Timestamp.valueOf("2023-07-09 12:34:56"))
                .build();

        screeningsRepository.saveAll(Arrays.asList(screening1,screening2));

        SeatEntity seat1=SeatEntity.builder()
                .row(1)
                .number(1)
                .auditorium(auditorium1)
                .build();

        SeatEntity seat2=SeatEntity.builder()
                .row(1)
                .number(2)
                .auditorium(auditorium1)
                .build();
        SeatEntity seat3=SeatEntity.builder()
                .row(2)
                .number(1)
                .auditorium(auditorium1)
                .build();
        SeatEntity seat4=SeatEntity.builder()
                .row(2)
                .number(2)
                .auditorium(auditorium1)
                .build();

        seatsRepository.saveAll(Arrays.asList(seat1,seat2,seat3,seat4));

        ReservationEntity reservation1=ReservationEntity.builder()
                .screening(screening1)
                .user_reserved(user1)
                .paid(true)
                .active(true)
                .build();

        reservationsRepository.saveAll(Arrays.asList(reservation1));

        SeatReservedEntity seatReserved1=SeatReservedEntity.builder()
                .reservation(reservation1)
                .seat(seat1)
                .screening(screening1)
                .build();

        SeatReservedEntity seatReserved2=SeatReservedEntity.builder()
                .reservation(reservation1)
                .seat(seat2)
                .screening(screening1)
                .build();

        seatsReservedRepository.saveAll(Arrays.asList(seatReserved1,seatReserved2));
    }

}
