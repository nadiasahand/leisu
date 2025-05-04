package com.mycompany.database.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMovie is a Querydsl query type for Movie
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovie extends EntityPathBase<Movie> {

    private static final long serialVersionUID = 2058943189L;

    public static final QMovie movie = new QMovie("movie");

    public final StringPath description = createString("description");

    public final StringPath director = createString("director");

    public final StringPath genre = createString("genre");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> publicYear = createNumber("publicYear", Integer.class);

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> watchedTime = createNumber("watchedTime", Long.class);

    public QMovie(String variable) {
        super(Movie.class, forVariable(variable));
    }

    public QMovie(Path<? extends Movie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovie(PathMetadata metadata) {
        super(Movie.class, metadata);
    }

}

