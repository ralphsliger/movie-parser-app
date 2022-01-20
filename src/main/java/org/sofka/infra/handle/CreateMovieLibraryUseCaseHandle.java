package org.sofka.infra.handle;

import io.quarkus.vertx.ConsumeEvent;
import org.sofka.domain.movielibrary.command.CreateMovieLibraryCommand;
import org.sofka.infra.generic.UseCaseHandle;
import org.sofka.usecase.CreateMovieLibraryUseCase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateMovieLibraryUseCaseHandle extends UseCaseHandle {

    private final CreateMovieLibraryUseCase createMovieLibraryUseCase;

    public CreateMovieLibraryUseCaseHandle(CreateMovieLibraryUseCase createMovieLibraryUseCase) {
        this.createMovieLibraryUseCase = createMovieLibraryUseCase;
    }

    @ConsumeEvent(value = "sofka.createlibrary")
    void consumeBlocking(CreateMovieLibraryCommand command) {
        var events = createMovieLibraryUseCase.apply(command);
        saveLibrary(command.getLibraryId(), events);
    }


}
