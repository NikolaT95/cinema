package com.NikolaTabas94rn.cinema.model;

import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import com.NikolaTabas94rn.cinema.model.mapper.MovieMapper;
import com.NikolaTabas94rn.cinema.model.mapper.MovieMapperImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MovieMapperTest {


    private MovieMapper mapper = new MovieMapperImpl();

    @Test
    public void shouldConvertToDto() {

        final MovieEntity movieEntity = MovieEntity.builder()
                .id(1)
                .title("Sample Movie")
                .director("John Doe")
                .genre("Action")
                .description("A thrilling action movie")
                .duration_min(120)
                .build();

        final MovieDto expectedDto = MovieDto.builder()
                .id(1)
                .title("Sample Movie")
                .director("John Doe")
                .genre("Action")
                .description("A thrilling action movie")
                .duration_min(120)
                .build();

        final MovieDto actualDto = mapper.toDto(movieEntity);

        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    public void shouldConvertToEntity() {
        // Prepare
        MovieSaveDto movieSaveDto = MovieSaveDto.builder()
                .title("Sample Movie")
                .director("John Doe")
                .genre("Action")
                .description("A thrilling action movie")
                .duration_min(120)
                .build();

        MovieEntity expectedEntity = MovieEntity.builder()
                .id(0)
                .title("Sample Movie")
                .director("John Doe")
                .genre("Action")
                .description("A thrilling action movie")
                .duration_min(120)
                .build();

        MovieEntity actualEntity = mapper.toEntity(movieSaveDto);


        assertThat(actualEntity).isEqualTo(expectedEntity);
    }

}
