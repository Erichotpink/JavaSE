package com.epam.javase.t02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represent an employee. Each employee has name, surname, unique ID and list of stationery.
 *
 * Created by aivanov on 2/27/2017.
 */
public class Employee {

    private static int lastID = 0;

    private final String name;
    private final String surname;
    private final int id;
    private final List<Stationery> stationery = new ArrayList<>();

    public Employee(String name, String surname) {
        this(name, surname, Collections.emptyList());
    }

    public Employee(String name, String surname, List<Stationery> stationery) {
        this.name = name;
        this.surname = surname;
        this.stationery.addAll(stationery);
        this.id = ++lastID;
    }


    /**
     * Add a new stationery.
     *
     * @param obj stationery to be added
     */
    public void addStationery(Stationery obj) {
        Objects.requireNonNull(obj, "The argument stationery cannot be null.");

        stationery.add(obj);
    }


    /**
     * Remove the stationery.
     *
     * @param obj stationery to be removed
     */
    public void removeStationery(Stationery obj) {
        Objects.requireNonNull(obj, "The argument stationery cannot be null.");

        stationery.remove(obj);
    }


    /**
     * Return total cost of all stationeries.
     *
     * @return total cost
     */
    public double getStationeryTotalCost() {
        double ans = 0.0;

        if (stationery.isEmpty()) {
            return ans;
        }

        for (Stationery temp : stationery) {
            ans += temp.getCost();
        }

        return ans;
    }

    public int getID() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return (id == employee.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", stationery=" + stationery +
                '}';
    }
}
