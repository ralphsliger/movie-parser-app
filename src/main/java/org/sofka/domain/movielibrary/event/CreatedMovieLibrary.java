package org.sofka.domain.movielibrary.event;

import org.sofka.domain.generic.DomainEvent;

public class CreatedMovieLibrary extends DomainEvent {
    private String name;

    public CreatedMovieLibrary(String name) {
        super("sofka.createdlibrary");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
