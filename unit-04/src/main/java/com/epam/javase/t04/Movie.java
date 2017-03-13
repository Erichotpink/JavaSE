package com.epam.javase.t04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a simple movie.
 *
 * Created by aivanov on 3/13/2017.
 */
public class Movie implements Serializable{

    private final String name;
    private final Set<Actor> actors;

    public Movie(String name) {
        this(name, new HashSet<>());
    }

    public Movie(String name, Set<Actor> actors) {
        this.name = name;
        this.actors = new HashSet<>(actors);
    }


    public String getName() {
        return name;
    }

    public List<Actor> getActors() {
        return new ArrayList<Actor>(actors);
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void removeActor(Actor actor) {
        actors.remove(actor);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", actors=" + actors.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!name.equals(movie.name)) return false;
        return actors.equals(movie.actors);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + actors.hashCode();
        return result;
    }
}
