package com.mycompany.database.repositories.imps;

import com.mycompany.database.models.Movie;
import com.mycompany.database.models.QMovie;
import com.mycompany.database.models.criteria.MovieCriteria;
import com.mycompany.database.repositories.MovieRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieQdslImp extends AbstractQdslImpl<Movie, MovieCriteria> implements MovieRepository {

    protected MovieQdslImp(EntityManager em) {
        super(em, Movie.class);
    }

    @Override
    public List<Movie> getByCriteria(MovieCriteria criteria) {
        return this.queryFactory.selectFrom(QMovie.movie).where(this.getPredicate(criteria)).fetch();
    }

    @Override
    protected BooleanBuilder getPredicate(MovieCriteria criteria) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(this.getPredicate(criteria.getPublishYearFilter(), QMovie.movie.publicYear));
        builder.and(this.getPredicate(criteria.getRatingFilter(), QMovie.movie.rating));
        return builder;
    }
}
