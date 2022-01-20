package org.sofka.usecase;

import org.sofka.domain.generic.DomainEvent;
import org.sofka.domain.movielibrary.MovieLibrary;
import org.sofka.domain.movielibrary.command.CreateMovieLibraryCommand;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CreateMovieLibraryUseCase implements Function<CreateMovieLibraryCommand, List<DomainEvent>> {
    @Override
    public List<DomainEvent> apply(CreateMovieLibraryCommand command) {
        var library = new MovieLibrary(command.getLibraryId(), command.getName());
        return library.getUncommittedChanges();

    }
}
