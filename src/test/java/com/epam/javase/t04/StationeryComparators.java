package com.epam.javase.t04;

import org.junit.Before;
import org.junit.Test;
import com.epam.javase.t03.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/1/2017.
 */
public class StationeryComparators {

    private final Pen pen = new Pen(50.0);
    private final Pen pen2 = new Pen(30.0);
    private final Pencil pencil = new Pencil(20.0);
    private final Notepad notepad = new Notepad(30, 100);
    private final Ruler ruler = new Ruler(30, 50);

    private final List<Stationery> list = new ArrayList<>();

    @Test
    public void testStationeryComparators() {
        list.add(pen);
        list.add(pencil);
        list.add(notepad);
        list.add(ruler);
        list.add(pen2);
        list.add(null);
        list.add(null);

        list.sort(new StationeryPriceComparator());
        System.out.println("Sorted by price: " + System.lineSeparator() + list);

        list.sort(new StationeryNameComparator());
        System.out.println("Sorted by name: " + System.lineSeparator() + list);

        list.sort(new StationeryPriceAndNameComparator());
        System.out.println("Sorted by price and then by name: " + System.lineSeparator() + list);
    }

}