package com.epam.javase.t04;

import java.util.ArrayList;
import java.util.List;

/**
 * Repsresents a stationery set.
 *
 * Created by aivanov on 3/1/2017.
 */
public class StationerySet {

    private final List<Stationery> list = new ArrayList<>();

    public void addStationery(Stationery item) {
        list.add(item);
    }

    public double getTotalCost() {
        return list.stream().mapToDouble( (s) -> s.getPrice()).sum();
    }

    public List<Stationery> getAllStationery() {
        return new ArrayList<Stationery>(list);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("StationerySet{");

        for (Stationery s : list) {
            str.append(System.lineSeparator() + "Name: " + s.getName() + ", Price: " + s.getPrice());
        }
        str.append(System.lineSeparator() + '}');

        return str.toString();

    }
}
