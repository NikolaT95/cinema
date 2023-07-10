package com.NikolaTabas94rn.cinema.repository.specification;

import com.NikolaTabas94rn.cinema.model.api.movie.MovieSearchOption;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningSearchOption;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity_;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ScreeningSearchSpecification implements Specification<ScreeningEntity> {
    private final ScreeningSearchOption screeningSearchOption;
    @Override
    public Predicate toPredicate(Root<ScreeningEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates=new ArrayList<>();

        Path<Timestamp> startingTime=root.get(ScreeningEntity_.screeningStart);

        root.fetch(ScreeningEntity_.auditorium);
        root.fetch(ScreeningEntity_.movie);
        if(screeningSearchOption.getSpecificDate()!=null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate specificDate = LocalDate.parse(screeningSearchOption.getSpecificDate(), formatter);

            Timestamp startOfDay = Timestamp.valueOf(specificDate.atStartOfDay());
            Timestamp endOfDay = Timestamp.valueOf(specificDate.plusDays(1).atStartOfDay());

            predicates.add(criteriaBuilder.greaterThanOrEqualTo(startingTime, startOfDay));
            predicates.add(criteriaBuilder.lessThan(startingTime, endOfDay));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
