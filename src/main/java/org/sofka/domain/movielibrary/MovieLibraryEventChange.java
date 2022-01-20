package org.sofka.domain.movielibrary;


import org.sofka.domain.generic.EventChange;
import org.sofka.domain.movielibrary.event.AddedMovie;
import org.sofka.domain.movielibrary.event.CreatedMovieLibrary;

import java.util.HashMap;

public class MovieLibraryEventChange implements EventChange {
    public MovieLibraryEventChange(MovieLibrary movieLibrary) {
        listener((CreatedMovieLibrary event)->{
            movieLibrary.name= event.getName();
            movieLibrary.library = new HashMap<>();
        });
        listener((AddedMovie event)->{
            var movie = new Movie(event.getMovieId(), event.getTitle(), event.getYear(), event.getGenre(), event.getSynopsis(), event.getDuration(), event.getCoverUrl(), event.getMovieUrl());
            movieLibrary.library.put(event.getTitle(), movie);
        });
    }
}
