package com.epam.javase.t04;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/13/2017.
 */
public class MovieCollectionSerializatorTest {

    private MovieCollection comedies;
    private MovieCollection horrors;

    private final Actor[] actors = {
            new Actor("Andrey"),
            new Actor("Anton"),
            new Actor("Alex")
    };

    private final Movie[] movies = {
            new Movie("Psycho"),
            new Movie("Aliens"),
            new Movie("Aliens 4")
    };

    @Before
    public void initalizeMovieCollection() throws Exception {
        movies[0].addActor(actors[0]);
        movies[0].addActor(actors[1]);
        movies[1].addActor(actors[1]);
        movies[1].addActor(actors[2]);
        movies[2].addActor(actors[1]);
        movies[2].addActor(actors[2]);
    }

    @Test
    public void loadedColectionShouldBeEqualToSaved() throws Exception {

        MovieCollection collection = new MovieCollection("Horrors", new HashSet<Movie>(Arrays.asList(movies)));

        File output = new File("c:/temp/collection.txt");
        collection.save(output);

        MovieCollection saved = MovieCollection.load(output);

        assertTrue(collection.equals(saved));
    }

    @Test
    public void loadFromFileAndModify() throws Exception {
        MovieCollection collection = new MovieCollection("Horrors", new HashSet<Movie>(Arrays.asList(movies)));

        File output = new File("c:/temp/collection.txt");
        collection.save(output);

        MovieCollection saved = MovieCollection.load(output);
        saved.removeMovie(movies[0]);
        saved.save(output);

        assertFalse(collection.equals(MovieCollection.load(output)));
    }

    @Test
    public void shouldCreateOneObjectForMultipleEqualsReferences() {
        MovieCollection collection = new MovieCollection("Horrors");
        collection.addMovie(movies[1]);
        collection.addMovie(movies[2]);

        File output = new File("c:/temp/collection.txt");
        collection.save(output);

        MovieCollection saved = MovieCollection.load(output);

        Actor a1 = saved.getMovies().get(0).getActors().get(0);
        Actor a2 = saved.getMovies().get(1).getActors().get(0);
        Actor a3 = saved.getMovies().get(1).getActors().get(1);
        assertTrue(a1 == a2);
        assertFalse(a1 == a3);
    }

}
