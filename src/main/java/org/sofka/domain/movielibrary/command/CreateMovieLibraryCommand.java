package org.sofka.domain.movielibrary.command;

import org.sofka.domain.generic.Command;

public class CreateMovieLibraryCommand extends Command {
    private String libraryId;
    private String name;

    public CreateMovieLibraryCommand() {
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
