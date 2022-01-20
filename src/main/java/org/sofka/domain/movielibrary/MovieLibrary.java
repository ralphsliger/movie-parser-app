package org.sofka.domain.movielibrary;

import org.sofka.domain.generic.AggregateRoot;
import org.sofka.domain.generic.DomainEvent;
import org.sofka.domain.movielibrary.event.AddedMovie;
import org.sofka.domain.movielibrary.event.CreatedMovieLibrary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieLibrary extends AggregateRoot {

    protected Map<String, Movie> library;
    protected String name;

    public MovieLibrary(String libraryId, String name) {
        super(libraryId);
        subscribe(new MovieLibraryEventChange(this));
        library = new HashMap<>();
        appendChange(new CreatedMovieLibrary(name)).apply();
    }

    public MovieLibrary(String id) {
        super(id);
        subscribe(new MovieLibraryEventChange(this));
    }

    public static MovieLibrary from(String id, List<DomainEvent> events){
        var library = new MovieLibrary(id);
        events.forEach(library::applyEvent);
        return library;
    }

    public void addMovie(String movieId, String title, String year, String genre, String synopsis, String duration, String coverUrl, String movieUrl){
        appendChange(new AddedMovie(movieId, title, year, genre, synopsis, duration,coverUrl, movieUrl )).apply();
    }

    public Map<String, Movie> Library() {
        return library;
    }

    public String Name() {
        return name;
    }
}
