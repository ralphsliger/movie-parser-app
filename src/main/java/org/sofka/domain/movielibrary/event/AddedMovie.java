package org.sofka.domain.movielibrary.event;

import org.sofka.domain.generic.DomainEvent;

public class AddedMovie extends DomainEvent {
    private String movieId;
    private String title;
    private String year;
    private String genre;
    private String synopsis;
    private String duration;
    private String coverUrl;
    private String movieUrl;

    public AddedMovie(String movieId, String title, String year, String genre, String synopsis,
                      String duration, String coverUrl, String movieUrl) {
        super("sofka.addedmovie");
        this.movieId = movieId;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.synopsis = synopsis;
        this.duration = duration;
        this.coverUrl = coverUrl;
        this.movieUrl = movieUrl;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getDuration() {
        return duration;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getMovieUrl() {
        return movieUrl;
    }
}
