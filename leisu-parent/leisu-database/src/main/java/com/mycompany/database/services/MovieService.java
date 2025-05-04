package com.mycompany.database.services;

import com.mycompany.database.models.Movie;
import com.mycompany.database.models.criteria.MovieCriteria;
import com.mycompany.database.models.requests.MovieCreateRequest;
import com.mycompany.database.repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends AbstractService<Movie, MovieCriteria, MovieRepository> {
    protected MovieService(MovieRepository repository) {
        super(repository);
    }

    public Movie save(MovieCreateRequest movieCreateRequest) {
        Movie movie = movieCreateRequest.toModel();
        return save(movie);
    }


}
