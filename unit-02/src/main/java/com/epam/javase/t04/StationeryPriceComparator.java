package com.epam.javase.t04;

import java.util.Comparator;
import com.epam.javase.t03.Stationery;

/**
 * The class implements comparision method to sort stationery by their price.
 *
 * Created by aivanov on 3/2/2017.
 */
public class StationeryPriceComparator implements Comparator<Stationery> {

    @Override
    public int compare(Stationery o1, Stationery o2) {
        if (o1 == o2) return 0;
        if (o1 == null) return -1;
        if (o2 == null) return 1;

        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
