package com.epam.javase.t04;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/1/2017.
 */
public class StationerySetTest {

    private final Pen pen = new Pen(50.0);
    private final Pencil pencil = new Pencil(20.0);
    private final Notepad notepad = new Notepad(100, 100);
    private final Ruler ruler = new Ruler(30, 50);

    private final StationerySet set = new StationerySet();

    @Before
    public void setInitialization() {
        set.addStationery(pen);
        set.addStationery(pencil);
        set.addStationery(notepad);
        set.addStationery(ruler);
    }

    @Test
    public void testStationerySetTotalCostAndAddStationery() throws Exception {
        assertTrue(Double.compare(set.getTotalCost(), 200.0) == 0);
    }

    @Test
    public void testStationeryToString() {

        String str = set.toString();

        for (Stationery s : set.getAllStationery()) {
            assertTrue(str.contains(s.getName()));
            assertTrue(str.contains(Double.toString(s.getPrice())));
        }

    }

}