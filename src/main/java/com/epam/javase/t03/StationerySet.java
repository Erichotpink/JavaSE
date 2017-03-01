package com.epam.javase.t03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
}
