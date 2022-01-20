package org.sofka.infra.entrypoint;

import io.vertx.mutiny.core.eventbus.EventBus;
import org.sofka.domain.movielibrary.command.AddMovieCommand;
import org.sofka.domain.movielibrary.command.CreateMovieLibraryCommand;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class CommandController {
    private final EventBus bus;

    public CommandController(EventBus bus){
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createMovieLibrary")
    public Response executor(CreateMovieLibraryCommand command){
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addMovie")
    public Response executor(AddMovieCommand command){
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }
}
