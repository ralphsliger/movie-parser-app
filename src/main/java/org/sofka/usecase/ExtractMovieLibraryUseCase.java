package org.sofka.usecase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.sofka.domain.generic.DomainEvent;
import org.sofka.domain.generic.EventStoreRepository;
import org.sofka.domain.movielibrary.MovieLibrary;
import org.sofka.domain.movielibrary.command.AddMovieCommand;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

@Dependent
public class ExtractMovieLibraryUseCase implements Function<AddMovieCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;

    String URL_BASE = "https://pelisplus.so";

    public ExtractMovieLibraryUseCase(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AddMovieCommand command) {

        var movielibrary = MovieLibrary.from(command.getLibraryId(),
                repository.getEventsBy("movielibrary", command.getLibraryId())
        );

        try {
            for(String url: movies()){

                Document doc = Jsoup.connect(url).get();
                //Get main root
                Elements rawContent = doc.getElementsByClass("info-content");

                // Get content root
                Elements content = rawContent.get(0).getElementsByClass("content-type");

                String title = content.get(0).nextElementSibling().text();
                String year = content.get(1).nextElementSibling().text();
                String genre = content.get(3).nextElementSibling().text();
                String synopsis = content.get(4).nextElementSibling().text();
                String duration = content.get(2).nextElementSibling().text();

                //Getting Poster Url
                String posterContent =
                        doc.getElementsByClass("poster").get(0).children().get(0).attributes().asList().get(1).toString();
                String url_poster = posterContent.substring(5, posterContent.length() - 1);

                //Getting Video URL
                Elements iframe = doc.getElementsByClass("tabs-video hidden");
                Elements categoryVideo = iframe.get(0).getElementById("level2_subtitulado").getElementsByTag("li");
                String getFirstElementURL =
                        categoryVideo.get(0).tagName("data-video").attributes().asList().get(1).toString();
                String videoURL = getFirstElementURL.substring(12, getFirstElementURL.length() - 1);

                movielibrary.addMovie(command.getMovieId(),title, year, genre, synopsis, duration, url_poster, videoURL);

            }
        } catch (IOException e) {
            throw new ExtractMovieException();
        }
        return movielibrary.getUncommittedChanges();

    }

    public List<String> movies() throws IOException {

        //Connection
        final String URL = "https://pelisplus.so/estrenos";
        Document doc = Jsoup.connect(URL).get();

        //Get List of avaliable movie's
        Elements rawContent = doc.getElementsByClass("items-peliculas load-content");
        Element main = rawContent.get(0);
        Elements tag = main.getElementsByTag("a");
        List<String> movies = new ArrayList<>();
        //Get each movies link
        for (Element e : tag) {
            String atributte = e.attributes().toString();
            String text = atributte.substring(7, atributte.length() - 1);
            movies.add(URL_BASE.concat(text));
        }

        return movies;
    }
}
