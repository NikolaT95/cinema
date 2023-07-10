package com.NikolaTabas94rn.cinema.repository.specification;

import com.NikolaTabas94rn.cinema.model.api.movie.MovieSearchOption;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity_;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class MoviesSearchSpecification implements Specification<MovieEntity> {

    private final MovieSearchOption movieSearchOption;
    @Override
    public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates=new ArrayList<>();

        Path<String> title=root.get(MovieEntity_.title);
        Path<String> director=root.get(MovieEntity_.director);
        Path<String> genre=root.get(MovieEntity_.genre);
        Path<Integer> duration=root.get(MovieEntity_.duration_min);

        MovieSearchOption.SortByField sortBy=movieSearchOption.getSortBy();

        if(sortBy != null){
            Path<?> propertyToSortBy=null;

            switch (sortBy){
                case title:
                    propertyToSortBy=title;
                    break;
                case director:
                    propertyToSortBy=director;
                    break;
                case duration:
                    propertyToSortBy=duration;
                    break;
            }

            Sort.Direction direction=movieSearchOption.getSortDirection();
            if(direction==null || direction==Sort.Direction.ASC){
                query.orderBy(criteriaBuilder.asc(propertyToSortBy));
            }
            else{
                query.orderBy(criteriaBuilder.desc(propertyToSortBy));
            }
        }

        if(movieSearchOption.getGenre()!=null){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(genre),
                    "%"+movieSearchOption.getGenre().toLowerCase()+"%"));
        }
        if(movieSearchOption.getTitle()!=null){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(title),
                    "%"+movieSearchOption.getTitle().toLowerCase()+"%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
