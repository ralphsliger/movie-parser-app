package org.sofka.infra.materialize;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;
import org.sofka.domain.movielibrary.event.AddedMovie;
import org.sofka.domain.movielibrary.event.CreatedMovieLibrary;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class MovieLibraryHandle {

    private final MongoClient mongoClient;

    public MovieLibraryHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value = "sofka.createdlibrary", blocking = true)
    void consumeCatalogoCreado(CreatedMovieLibrary event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getName());

        mongoClient.getDatabase("queries")
                .getCollection("movielibrary")
                .insertOne(new Document(document));
    }

    @ConsumeEvent(value = "sofka.addedmovie", blocking = true)
    void consumePeliculaAsignada(AddedMovie event) {
        BasicDBObject document = new BasicDBObject();
        document.put("movie."+event.getTitle().strip()+".title", event.getTitle());
        document.put("movie."+event.getTitle().strip()+".year", event.getYear());
        document.put("movie."+event.getTitle().strip()+".genre", event.getTitle());
        document.put("movie."+event.getTitle().strip()+".synopsis", event.getGenre());
        document.put("movie."+event.getTitle().strip()+".duration", event.getDuration());
        document.put("movie."+event.getTitle().strip()+".coverUrl", event.getCoverUrl());
        document.put("movie."+event.getTitle().strip()+".movieUrl", event.getMovieUrl());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("movielibrary")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }

}
