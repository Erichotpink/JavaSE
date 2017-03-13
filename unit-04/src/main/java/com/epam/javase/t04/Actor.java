package com.epam.javase.t04;

import java.io.Serializable;

/**
 * Represents an actor.
 *
 * Created by aivanov on 3/13/2017.
 */
public class Actor implements Serializable{

    private String name;

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return "Actor [" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        return name.equals(actor.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
