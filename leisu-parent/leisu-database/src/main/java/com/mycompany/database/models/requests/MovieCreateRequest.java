package com.mycompany.database.models.requests;

import com.mycompany.database.models.Movie;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieCreateRequest extends AbstractRequest<Movie> {
    private String title;
    private String genre;
    private int publicYear;
    private String director;
    private String description;
    private int rating;

    public Movie toModel() {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setPublicYear(publicYear);
        movie.setDirector(director);
        movie.setDescription(description);
        movie.setRating(rating);
        return movie;
    }
}
