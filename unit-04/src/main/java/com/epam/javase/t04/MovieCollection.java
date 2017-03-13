package com.epam.javase.t04;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a simple movies collection.
 *
 * Created by aivanov on 3/13/2017.
 */
public class MovieCollection implements Serializable {

    private final String name;
    private final Set<Movie> movies;

    public MovieCollection(String name) {
        this(name, new HashSet<Movie>());
    }

    public MovieCollection(String name, Set<Movie> movies) {
        this.name = name;
        this.movies = new HashSet<>(movies);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) { movies.remove(movie);}

    public void save(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MovieCollection load(File file) {
        MovieCollection temp = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            temp = (MovieCollection) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public String getName() {
        return name;
    }

    public List<Movie> getMovies() {
        return new ArrayList<Movie>(movies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieCollection that = (MovieCollection) o;

        if (!name.equals(that.name)) return false;
        return movies.equals(that.movies);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + movies.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MovieCollection{" +
                "name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
