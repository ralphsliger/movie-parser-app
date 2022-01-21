package org.sofka.domain.movielibrary;

import java.util.Objects;

public class Movie {
    private final String id;
    private final String title;
    private final String year;
    private final String genre;
    private final String synopsis;
    private final String duration;
    private final String coverUrl;
    private final String movieUrl;

    public Movie(String id, String title, String year, String genre, String synopsis, String duration,
                 String coverUrl, String movieUrl) {
        this.id = id;
        this.title = Objects.requireNonNull(title);
        this.year = Objects.requireNonNull(year);
        this.genre = Objects.requireNonNull(genre);
        this.synopsis = Objects.requireNonNull(synopsis);
        this.duration = Objects.requireNonNull(duration);
        this.coverUrl = Objects.requireNonNull(coverUrl);
        this.movieUrl = Objects.requireNonNull(movieUrl);
    }

    public String Id() {
        return id;
    }

    public String Title() {
        return title;
    }

    public String Year() {
        return year;
    }

    public String Genre() {
        return genre;
    }

    public String Synopsis() {
        return synopsis;
    }

    public String Duration() {
        return duration;
    }

    public String CoverUrl() {
        return coverUrl;
    }

    public String MovieUrl() {
        return movieUrl;
    }




}
