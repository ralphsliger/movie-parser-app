package org.sofka.infra.generic;

import org.sofka.domain.generic.DomainEvent;
import org.sofka.domain.generic.EventSerializer;
import org.sofka.domain.generic.EventStoreRepository;
import org.sofka.domain.generic.StoredEvent;
import org.sofka.infra.message.BusService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class UseCaseHandle {
    @Inject
    private EventStoreRepository repository;

    @Inject
    private BusService busService;

    public void saveLibrary(String libraryId, List<DomainEvent> events){
        events.stream().map(event -> {
        String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("movielibrary", libraryId, storedEvent));

        events.forEach(busService::send);
    }

}
