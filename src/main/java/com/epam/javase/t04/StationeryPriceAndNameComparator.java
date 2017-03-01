package com.epam.javase.t04;

import java.util.Comparator;

/**
 * The class implements comparision method to sort stationery by their price and name.
 *
 * Created by aivanov on 3/2/2017.
 */
public class StationeryPriceAndNameComparator implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {
        if (o1 == o2) return 0;
        if (o1 == null) return -1;
        if (o2 == null) return 1;

        int result = Double.compare(o1.getPrice(), o2.getPrice());

        if (result == 0) {
            return o1.getName().compareTo(o2.getName());
        }

        return result;
    }
}
