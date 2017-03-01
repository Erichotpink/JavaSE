package com.epam.javase.t04;

import com.epam.javase.t03.Notepad;
import com.epam.javase.t03.Pen;
import com.epam.javase.t03.Pencil;
import com.epam.javase.t03.Ruler;
import com.epam.javase.t03.StationerySet;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/1/2017.
 */
public class StationerySetTest {
    private com.epam.javase.t03.Pen pen = new Pen(50.0);

    private Pencil pencil = new Pencil(20.0);
    private Notepad notepad = new Notepad(100, 100);
    private Ruler ruler = new Ruler(30, 50);

    @Test
    public void testStationerySetTotalCostAndAddStationery() {

        StationerySet set = new StationerySet();
        set.addStationery(pen);
        set.addStationery(pencil);
        set.addStationery(notepad);
        set.addStationery(ruler);

        assertTrue(Double.compare(set.getTotalCost(), 200.0) == 0);

    }

}