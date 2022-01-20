package org.sofka.infra.handle;

import io.quarkus.vertx.ConsumeEvent;
import org.sofka.domain.movielibrary.command.AddMovieCommand;
import org.sofka.infra.generic.UseCaseHandle;
import org.sofka.usecase.ExtractMovieLibraryUseCase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddMovieUseCaseHandle extends UseCaseHandle {
    private final ExtractMovieLibraryUseCase extractMovieUseCase;

    public AddMovieUseCaseHandle(ExtractMovieLibraryUseCase extractMovieUseCase) {
        this.extractMovieUseCase = extractMovieUseCase;
    }

    @ConsumeEvent(value = "sofka.addmovie")
    void consumeBlocking(AddMovieCommand command) {
        var events = extractMovieUseCase.apply(command);
        saveLibrary(command.getLibraryId(), events);
    }

}
