package com.mycompany.api.controllers;

import com.mycompany.database.models.Movie;
import com.mycompany.database.models.criteria.MovieCriteria;
import com.mycompany.database.models.requests.MovieCreateRequest;
import com.mycompany.database.repositories.MovieRepository;
import com.mycompany.database.services.MovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController extends AbstractController<Movie, MovieCriteria, MovieRepository, MovieService> {

    public MovieController(MovieService movieService) {
        super(movieService);
    }

    @PostMapping
    public Movie create(@RequestBody MovieCreateRequest request) {
        return super.service.save(request);
    }
}
