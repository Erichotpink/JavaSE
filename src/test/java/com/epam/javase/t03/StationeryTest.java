package com.epam.javase.t03;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/1/2017.
 */
public class StationeryTest {

    private Pen pen = new Pen(50.0);
    private Pencil pencil = new Pencil(10.0);


    @Test
    public void testStationeryTotalCost() {

        List<Stationery> list = new ArrayList<>();
        list.add(pen);
        list.add(pencil);

        double totalCost = 0.0;

        for (Stationery s : list) {
            totalCost += s.getPrice();
        }

        assertTrue(Double.compare(totalCost, 60.0) == 0);

    }
}