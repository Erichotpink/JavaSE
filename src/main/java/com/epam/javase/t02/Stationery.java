package com.epam.javase.t02;

import java.util.Objects;

/**
 * Created by aivanov on 2/27/2017.
 */
public class Stationery {

    private String name;
    private double cost;

    public Stationery(String name, double cost) {
        Objects.requireNonNull(name, "Name cannot be null.");
        if (Double.compare(cost, 0.0) < 0) {
            throw new IllegalArgumentException("Cost must be positive. Specified value: " + cost);
        }

        this.name = name;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Stationery obj = (Stationery) other;

        return (Double.compare(this.cost, obj.cost) == 0)
                && this.name.equals(obj.name);
    }
}
